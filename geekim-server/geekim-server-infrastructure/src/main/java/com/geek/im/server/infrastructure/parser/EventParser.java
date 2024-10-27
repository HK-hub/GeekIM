package com.geek.im.server.infrastructure.parser;

import com.geek.im.server.infrastructure.channel.CommunicationContext;

/**
 * @author : HK意境
 * @ClassName : EventParser
 * @date : 2024/8/30 17:00
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface EventParser<T> {

    public Object parseEvent(CommunicationContext context, T event);

}
