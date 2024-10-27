package com.geek.im.server.infrastructure.parser;

import geek.im.server.common.enums.ServerEventEnum;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author : HK意境
 * @ClassName : ServerEventHandler
 * @date : 2024/8/30 17:41
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Documented
@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServerEventHandler {

    /**
     * 订阅事件类型
     *
     * @return
     */
    ServerEventEnum type();

    /**
     * 事件实体类
     *
     * @return
     */
    Class<?> clazz();
}
