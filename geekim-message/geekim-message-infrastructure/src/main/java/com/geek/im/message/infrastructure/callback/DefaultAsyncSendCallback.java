package com.geek.im.message.infrastructure.callback;

import com.geek.im.message.infrastructure.assembly.MessageResultConverter;
import com.geek.im.support.domain.callback.MessageSendCallback;
import com.geek.im.support.domain.dto.MessageSendResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * @author : HK意境
 * @ClassName : DefaultAsyncSendCallback
 * @date : 2024/3/14 18:52
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
public class DefaultAsyncSendCallback implements MessageSendCallback, SendCallback {

    private static final DefaultAsyncSendCallback INSTANCE = new DefaultAsyncSendCallback();

    private DefaultAsyncSendCallback() {

    }

    @Override
    public void onSuccess(MessageSendResult sendResult) {
        log.info("sendResult:{}消息---发送MQ成功---", sendResult);

    }

    /**
     * RocketMQ 处理成功回调函数
     *
     * @param sendResult
     */
    @Override
    public void onSuccess(SendResult sendResult) {

        MessageSendResult messageSendResult = MessageResultConverter.INSTANCE.convertToResult(sendResult);
        this.onSuccess(messageSendResult);
    }

    @Override
    public void onException(Throwable throwable) {
        log.error("sendResult:消息---发送MQ失败 ex:{}---", throwable.getMessage());
    }


    public static DefaultAsyncSendCallback getInstance() {
        return INSTANCE;
    }


}
