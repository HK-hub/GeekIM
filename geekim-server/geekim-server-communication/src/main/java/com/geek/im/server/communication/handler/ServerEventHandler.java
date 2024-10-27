package com.geek.im.server.communication.handler;

import com.alibaba.fastjson.JSON;
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
     * @param context
     * @param baseServerEvent
     *
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext context, BaseServerEvent baseServerEvent) throws Exception {

        CommunicationContext communicationContext = (CommunicationContext) context.channel().attr(AttributeKey.valueOf(ChannelContextAttributeConstants.communicationContext)).get();

        String eventData = baseServerEvent.getData();
        ServerEventEnum serverEventEnum = baseServerEvent.getServerEventEnum();

        EventParser<Object> eventParser = this.serverEventParserFactory.getEventParser(serverEventEnum);
        Class<?> eventClazz = this.serverEventParserFactory.getEventClazz(serverEventEnum);
        Object eventObject = JSON.parseObject(eventData, eventClazz);

        eventParser.parseEvent(communicationContext, eventObject);
    }


}
