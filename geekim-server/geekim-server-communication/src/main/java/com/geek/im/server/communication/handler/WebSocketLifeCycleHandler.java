package com.geek.im.server.communication.handler;


import com.geek.im.server.common.constants.ChannelContextAttributeConstants;
import com.geek.im.server.domain.event.UserConnectedOnlineEvent;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * @author : HK意境
 * @ClassName : ClientMessageHandler
 * @date : 2023/1/4 18:52
 * @description : 处理监听 Text 文本事件
 * @Todo : 在此处进行ChanelGroup的维护
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Data
@Component
@ChannelHandler.Sharable
public class WebSocketLifeCycleHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> implements ApplicationContextAware {


    private ApplicationContext applicationContext;


    /**
     * 在此处进行ChanelGroup的维护
     *
     * @param ctx
     *
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("链接断开：{}", ctx.channel().remoteAddress());
        super.channelInactive(ctx);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("链接创建：{}", ctx.channel().remoteAddress());
        super.channelActive(ctx);
    }


    /**
     * 握手成功后，钩子回调函数
     *
     * @param ctx
     * @param evt
     *
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        // 握手完成事件
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete handshakeComplete) {

            log.info("触发WebSocket握手认证成功事件，进行移除AuthenticationHandler和管理Channel:{}", handshakeComplete);
            if (ctx.pipeline().get(AuthenticationHandler.class) != null) {
                // 当前连接已经认证过了，后续通信无需再次验证，所以及时移除此handler
                ctx.pipeline().remove(AuthenticationHandler.class);
                // 推送消息
                Attribute<Long> attr = ctx.channel().attr(AttributeKey.valueOf(ChannelContextAttributeConstants.userIdAttribute));
                Long userId = attr.get();
                // 登陆认证是否成功
                if (Objects.isNull(userId)) {
                    ctx.channel().close();
                }
                // 认证成功
                log.info("处理数据.....");
                // 发送用户上线事件
                this.applicationContext.publishEvent(new UserConnectedOnlineEvent());
            }
        }

        super.userEventTriggered(ctx, evt);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ctx.fireChannelRead(msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}