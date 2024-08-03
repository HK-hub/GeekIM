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

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        if (Objects.isNull(serverProperties)) {
            serverProperties = SpringUtils.getBean(IMServerProperties.class);
        }
        AuthenticationHandler authenticationHandler = SpringUtils.getBean(AuthenticationHandler.class);
        IMClientMessageHandler imClientMessageHandler = SpringUtils.getBean(IMClientMessageHandler.class);
        WebSocketLifeCycleHandler webSocketLifeCycleHandler = SpringUtils.getBean(WebSocketLifeCycleHandler.class);

        log.info("WebSocketIMServerChannelInitializer 初始化通道，配置信息：{}", serverProperties);

        this.socketChannel = socketChannel;
        ChannelPipeline pipeline = socketChannel.pipeline();

        // 用来判断是否是 读空闲时间过长，或者写空闲时间过长,30分钟 内没有收到 channel 的数据，就会触发一个事件
        pipeline.addLast(new IdleStateHandler(serverProperties.getServer().getMaxIdleTime(), 0, 0, TimeUnit.MINUTES));
        // 心跳处理器，ChannelDuplexHandler 可以同时作为入站和出战处理器
        pipeline.addLast(new HeartBeatEventHandler());
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
        pipeline.addLast("websocket-protocol-handler", new WebSocketServerProtocolHandler(this.serverProperties.getServer().getPath(), null, true));
        // WebSocket连接生命周期处理器
        pipeline.addLast("websocket-life-cycle-handler", webSocketLifeCycleHandler);
        // 自定义消息处理器
        pipeline.addLast("client-message-handler", imClientMessageHandler);
    }
}
