package com.geek.im.server.application.parser;

import com.geek.im.server.domain.event.message.MessageEvent;
import com.geek.im.server.infrastructure.channel.CommunicationContext;
import com.geek.im.server.infrastructure.parser.EventParser;
import com.geek.im.server.infrastructure.parser.ServerEventHandler;
import geek.im.server.common.enums.ServerEventEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : HK意境
 * @ClassName : MessageEventParser
 * @date : 2024/8/30 17:00
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@ServerEventHandler(type = ServerEventEnum.MESSAGE, clazz = MessageEvent.class)
public class MessageEventParser implements EventParser<MessageEvent> {


    /**
     * 解析为BaseMessage
     *
     * @param messageEvent
     *
     * @return
     */
    @Override
    public Object parseEvent(CommunicationContext context, MessageEvent messageEvent) {

        log.info("处理消息事件：messageEvent={}", messageEvent);

        return null;
    }
}
