package com.geek.im.server.communication.handler;

import com.alibaba.fastjson2.JSON;
import com.geek.im.server.domain.event.BaseServerEvent;
import com.geek.im.server.infrastructure.channel.CommunicationContext;
import com.geek.im.server.infrastructure.parser.EventParser;
import com.geek.im.server.infrastructure.parser.ServerEventParserFactory;
import geek.im.server.common.constants.ChannelContextAttributeConstants;
import geek.im.server.common.enums.ServerEventEnum;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author : HK意境
 * @ClassName : ServerEventHandler
 * @date : 2024/10/27 14:55
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class ServerEventHandler extends SimpleChannelInboundHandler<BaseServerEvent> {

    @Resource
    private ServerEventParserFactory serverEventParserFactory;

    public ServerEventHandler(@Autowired ServerEventParserFactory serverEventParserFactory) {
        this.serverEventParserFactory = serverEventParserFactory;
    }

    /**
     * 消息事件分派
     *
     * @param channelContext
     * @param baseServerEvent
     *
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelContext, BaseServerEvent baseServerEvent) throws Exception {

        CommunicationContext communicationContext = (CommunicationContext) channelContext.channel().attr(AttributeKey.valueOf(ChannelContextAttributeConstants.communicationContext)).get();
        if (Objects.isNull(communicationContext)) {
            communicationContext = new CommunicationContext(channelContext.channel(), channelContext);
            channelContext.channel().attr(AttributeKey.valueOf(ChannelContextAttributeConstants.communicationContext)).set(communicationContext);
        }

        // 获取事件内容
        String eventData = baseServerEvent.getData();
        ServerEventEnum serverEventEnum = baseServerEvent.getServerEventEnum();

        // 获取事件解析器
        EventParser<Object> eventParser = this.serverEventParserFactory.getEventParser(serverEventEnum);
        Class<?> eventClazz = this.serverEventParserFactory.getEventClazz(serverEventEnum);
        Object eventObject = JSON.parseObject(eventData, eventClazz);

        eventParser.parseEvent(communicationContext, eventObject);
    }


}
