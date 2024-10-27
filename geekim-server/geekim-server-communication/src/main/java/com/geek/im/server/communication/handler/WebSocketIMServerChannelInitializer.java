package com.geek.im.server.communication.handler;

import com.geek.im.server.domain.property.IMServerProperties;
import com.geek.im.server.infrastructure.util.SpringUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author : HK意境
 * @ClassName : WebSocketIMServerChannelInitializer
 * @date : 2024/4/27 13:28
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class WebSocketIMServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    // 维护与客户端的通道
    private SocketChannel socketChannel;

    /**
     * IM服务器配置
     */
    @Resource
    private IMServerProperties serverProperties;

    @Resource
    private AuthenticationHandler authenticationHandler;
    @Resource
    private ClientSimpleMessageHandler clientSimpleMessageHandler;
    @Resource
    private WebSocketLifeCycleHandler webSocketLifeCycleHandler;
    @Resource
    private Base64CodecHandler base64CodecHandler;
    @Resource
    private EncryptAndDecryptHandler encryptAndDecryptHandler;
    @Resource
    private ServerEventHandler serverEventHandler;

    private void initProperty() {

        if (Objects.isNull(serverProperties)) {
            serverProperties = SpringUtils.getBean(IMServerProperties.class);
        }
        log.info("WebSocketIMServerChannelInitializer 初始化通道，配置信息：{}", serverProperties);

        authenticationHandler = this.getChannelHandler(this.authenticationHandler, AuthenticationHandler.class);
        clientSimpleMessageHandler = this.getChannelHandler(this.clientSimpleMessageHandler, ClientSimpleMessageHandler.class);
        webSocketLifeCycleHandler = this.getChannelHandler(this.webSocketLifeCycleHandler, WebSocketLifeCycleHandler.class);
        base64CodecHandler = this.getChannelHandler(this.base64CodecHandler, Base64CodecHandler.class);
        encryptAndDecryptHandler = this.getChannelHandler(this.encryptAndDecryptHandler, EncryptAndDecryptHandler.class);
        serverEventHandler = this.getChannelHandler(this.serverEventHandler, ServerEventHandler.class);
    }


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        // 初始化配置和handler
        this.initProperty();

        this.socketChannel = socketChannel;
        ChannelPipeline pipeline = socketChannel.pipeline();

        // 用来判断是否是 读空闲时间过长，或者写空闲时间过长,30分钟 内没有收到 channel 的数据，就会触发一个事件
        pipeline.addLast(new IdleStateHandler(serverProperties.getServer().getMaxIdleTime(), 0, 0, TimeUnit.MINUTES));
        // 心跳处理器，ChannelDuplexHandler 可以同时作为入站和出战处理器
        pipeline.addLast(new HeartBeatHandler());
        pipeline.addLast("http-codec", new HttpServerCodec());
        // 对 HttpMessage 进行聚合处理，
        pipeline.addLast("aggregator", new HttpObjectAggregator(this.serverProperties.getServer().getMaxContentLength()));
        // 以块的形式写入
        pipeline.addLast("chunked-write", new ChunkedWriteHandler());
        // 压缩websocket 报文
        pipeline.addLast(new WebSocketServerCompressionHandler());
        // 将自定义的 WebSocketFrame 放在 WebSocketServerProtocolHandler 前解决 netty 不支持 uri 上带参数的问题，造成的 websocket 连接失败
        pipeline.addLast("websocket-connect-handler", authenticationHandler);
        // WebSocket协议处理器
        WebSocketServerProtocolHandler webSocketServerProtocolHandler = new WebSocketServerProtocolHandler(this.serverProperties.getServer().getPath(), null,
                true, this.serverProperties.getServer().getMaxFrameLength(), false, true);
        pipeline.addLast("websocket-protocol-handler", webSocketServerProtocolHandler);
        // 帧解码器，解析为String
        pipeline.addLast(SpringUtils.getBean(Base64CodecHandler.class));
        // WebSocket连接生命周期处理器
        pipeline.addLast("websocket-life-cycle-handler", webSocketLifeCycleHandler);
        // 自定义消息处理器
        pipeline.addLast("client-message-handler", this.clientSimpleMessageHandler);
        // Base64解码器
        pipeline.addLast("frame-base64-decode-handler", this.base64CodecHandler);
        // 数据解密器
        pipeline.addLast("data-decrypt-handler", this.encryptAndDecryptHandler);
        // 服务端事件处理器
        pipeline.addLast("server-event-handler", this.serverEventHandler);
    }


    private <T> T getChannelHandler(T handler, Class<T> handlerType) {

        if (Objects.nonNull(handler)) {
            return handler;
        }

        return SpringUtils.getBean(handlerType);
    }

}
