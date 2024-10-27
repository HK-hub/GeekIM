package com.geek.im.server.communication.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : HK意境
 * @ClassName : IMClientBinaryHandler
 * @date : 2024/4/29 20:55
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@ChannelHandler.Sharable
public class IMClientBinaryHandler extends SimpleChannelInboundHandler<BinaryWebSocketFrame> {


    @Override
    public boolean acceptInboundMessage(Object msg) throws Exception {

        return msg instanceof BinaryWebSocketFrame;
    }

    /**
     * 二进制消息处理器
     *
     * @param channelHandlerContext
     * @param binaryWebSocketFrame
     *
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, BinaryWebSocketFrame binaryWebSocketFrame) throws Exception {
        log.info("接收到二进制WebSocket包：{}", binaryWebSocketFrame.content().toString());
    }
}
