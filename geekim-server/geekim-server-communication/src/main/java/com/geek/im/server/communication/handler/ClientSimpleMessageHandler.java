package com.geek.im.server.communication.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author : HK意境
 * @ClassName : ClientSimpleMessageHandler
 * @date : 2024/4/29 18:57
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Data
@Component
@ChannelHandler.Sharable
public class ClientSimpleMessageHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Resource
    private ApplicationContext applicationContext;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端消息处理器已就绪");
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, TextWebSocketFrame webSocketFrame) throws Exception {

        String text = webSocketFrame.text();
        log.info("IM Server Receive Message Frame:{}", text);

        // 响应消息给客户端
        // TextWebSocketFrame responseTextFrame = new TextWebSocketFrame("服务端收到消息并且响应你：" + text);
        // context.writeAndFlush(responseTextFrame);
    }


}
