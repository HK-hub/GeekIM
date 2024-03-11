package com.geek.im.message.infrastructure.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author : HK意境
 * @ClassName : MessageConsumer
 * @date : 2024/3/10 20:36
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MessageConsumer {

    String topic();

}
