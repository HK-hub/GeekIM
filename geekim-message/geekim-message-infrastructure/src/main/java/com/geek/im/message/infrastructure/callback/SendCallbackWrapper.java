package com.geek.im.message.infrastructure.callback;

import com.geek.im.message.infrastructure.assembly.MessageResultConverter;
import com.geek.im.support.domain.callback.MessageSendCallback;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.stereotype.Component;

/**
 * @author : HK意境
 * @ClassName : SendCallbackWrapper
 * @date : 2024/3/14 19:11
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
public class SendCallbackWrapper {

    private static final MessageResultConverter resultConverter = MessageResultConverter.INSTANCE;


    /**
     * 包装rocketMQ 发送回调
     *
     * @param messageSendCallback
     *
     * @return
     */
    public static SendCallback rocketMQCallbackWrapper(MessageSendCallback messageSendCallback) {

        return new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                messageSendCallback.onSuccess(resultConverter.convertToResult(sendResult));
            }

            @Override
            public void onException(Throwable throwable) {
                messageSendCallback.onException(throwable);
            }
        };
    }


}
