package com.geek.im.server.communication.handler;


import com.geek.im.server.communication.manager.UserConnectManager;
import com.geek.im.server.domain.aggregate.UserInfo;
import com.geek.im.server.domain.event.connect.UserConnectedEvent;
import com.geek.im.server.domain.event.connect.UserDisconnectedEvent;
import com.geek.im.server.domain.value.ClientConnectRequest;
import com.geek.im.server.infrastructure.manager.UserLocalChannelManager;
import geek.im.server.common.constants.ChannelContextAttributeConstants;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import jakarta.annotation.Resource;
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

    @Resource
    private UserLocalChannelManager userLocalChannelManager;
    @Resource
    private UserConnectManager userConnectManager;

    private ApplicationContext applicationContext;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("链接创建：{}", ctx.channel().remoteAddress());
        super.channelActive(ctx);
    }


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
        this.applicationContext.publishEvent(new UserDisconnectedEvent());
        this.userConnectManager.disconnect(ctx.channel());
        super.channelInactive(ctx);
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
            }

            Channel channel = ctx.channel();
            // 推送消息
            Attribute<Long> userIdAttr = channel.attr(AttributeKey.valueOf(ChannelContextAttributeConstants.userIdAttribute));
            Long userId = userIdAttr.get();
            // 登陆认证是否成功
            if (Objects.isNull(userId)) {
                channel.close();
            }

            // 认证成功
            log.info("处理数据.....");
            ClientConnectRequest connectRequest = (ClientConnectRequest) channel.attr(AttributeKey.valueOf(ChannelContextAttributeConstants.connectRequestInfo)).get();
            UserInfo userInfo = (UserInfo) channel.attr(AttributeKey.valueOf(ChannelContextAttributeConstants.userBaseInfoAttribute)).get();

            // 用户连接上线，维护channel通道，在线状态等信息
            boolean connected = this.userConnectManager.connect(userId, channel);

            // 发送用户上线事件: 用于提醒推送，特别关心用户提醒等
            UserConnectedEvent onlineEvent = new UserConnectedEvent();
            onlineEvent.setConnectRequest(connectRequest).setChannel(channel).setUserInfo(userInfo);
            this.applicationContext.publishEvent(onlineEvent);
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