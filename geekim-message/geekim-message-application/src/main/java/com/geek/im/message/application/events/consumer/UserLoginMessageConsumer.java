package com.geek.im.message.application.events.consumer;

import com.geek.im.message.infrastructure.annotation.MessageConsumer;
import lombok.extern.slf4j.Slf4j;
import org.noear.folkmq.client.MqConsumeHandler;
import org.noear.folkmq.client.MqMessageReceived;

/**
 * @author : HK意境
 * @ClassName : UserLoginMessageConsumer
 * @date : 2024/3/10 22:43
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@MessageConsumer(topic = "user-login")
public class UserLoginMessageConsumer implements MqConsumeHandler {

    /**
     * 用户登录事件
     *
     * @param message
     *
     * @throws Exception
     */
    @Override
    public void consume(MqMessageReceived message) throws Exception {

        log.info("user login message:{}", message);
    }
}
