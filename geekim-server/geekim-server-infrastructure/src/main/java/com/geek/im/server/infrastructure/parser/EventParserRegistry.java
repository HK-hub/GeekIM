package com.geek.im.server.infrastructure.parser;

import geek.im.server.common.enums.ServerEventEnum;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : EventParserRegistry
 * @date : 2024/8/30 17:48
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Component
public class EventParserRegistry implements ApplicationRunner {

    private final Map<ServerEventEnum, EventParser> parserMap = new HashMap<>();

    private final Map<ServerEventEnum, Class<?>> eventTypeMap = new HashMap<>();

    @Resource
    private List<EventParser> parserList;

    /**
     * 获取事件解析器
     *
     * @param eventEnum
     * @param <T>
     *
     * @return
     */
    public <T> EventParser<T> getEventParser(ServerEventEnum eventEnum) {

        return parserMap.get(eventEnum);
    }


    /**
     * 获取事件类型
     *
     * @param eventEnum
     *
     * @return
     */
    public Class<?> getEventClass(ServerEventEnum eventEnum) {

        return eventTypeMap.get(eventEnum);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        for (EventParser parser : parserList) {
            ServerEventHandler mark = parser.getClass().getAnnotation(ServerEventHandler.class);
            if (mark != null) {
                parserMap.put(mark.type(), parser);
                eventTypeMap.put(mark.type(), mark.clazz());
            }
        }
    }
}
