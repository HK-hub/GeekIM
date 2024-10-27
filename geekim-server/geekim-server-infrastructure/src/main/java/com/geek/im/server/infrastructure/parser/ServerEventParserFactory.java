package com.geek.im.server.infrastructure.parser;

import geek.im.server.common.enums.ServerEventEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : HK意境
 * @ClassName : ServerEventParserFactory
 * @date : 2024/8/30 14:47
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
public class ServerEventParserFactory {

    @Resource
    private EventParserRegistry eventParserRegistry;


    public <T> EventParser<T> getEventParser(ServerEventEnum serverEventEnum) {

        return this.eventParserRegistry.getEventParser(serverEventEnum);
    }

    public Class<?> getEventClazz(ServerEventEnum serverEventEnum) {

        return this.eventParserRegistry.getEventClass(serverEventEnum);
    }

}
