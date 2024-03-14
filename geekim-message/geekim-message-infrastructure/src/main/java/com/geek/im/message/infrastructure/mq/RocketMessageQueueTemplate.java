package com.geek.im.message.infrastructure.mq;

import com.geek.im.message.infrastructure.assembly.MessageResultConverter;
import com.geek.im.message.infrastructure.callback.DefaultAsyncSendCallback;
import com.geek.im.message.infrastructure.callback.SendCallbackWrapper;
import com.geek.im.support.domain.callback.MessageSendCallback;
import com.geek.im.support.domain.dto.MessageSendResult;
import com.geek.im.support.infrastructure.message.MessageQueueTemplate;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author : HK意境
 * @ClassName : RocketMessageQueueTemplate
 * @date : 2024/3/10 19:01
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "geek.im.message.using.type", havingValue = "rocketmq")
public class RocketMessageQueueTemplate implements MessageQueueTemplate {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    private final MessageResultConverter messageResultConverter = MessageResultConverter.INSTANCE;


    /**
     * 发送消息
     *
     * @param topic
     * @param msg
     */
    @Override
    public <T> void sendMessage(String topic, T msg) {

        this.rocketMQTemplate.convertAndSend(topic, msg);
    }

    /**
     * 发送带tag的消息
     *
     * @param topic
     * @param tag
     * @param msg
     */
    @Override
    public <T> void sendMessage(String topic, String tag, T msg) {
        topic = topic + ":" + tag;
        this.rocketMQTemplate.send(topic, MessageBuilder.withPayload(msg).build());
    }

    /**
     * 发送同步消息（阻塞当前线程，等待broker响应发送结果，这样不太容易丢失消息）
     * sendResult为返回的发送结果
     *
     * @param topic
     * @param msg
     */
    @Override
    public <T> MessageSendResult syncSend(String topic, T msg) {

        SendResult sendResult = rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload(msg).build());
        return this.messageResultConverter.convertToResult(sendResult);
    }

    /**
     * 发送带tag的消息，直接在topic后面加上":tag"
     *
     * @param topic 消息主题
     * @param tag   消息tag
     * @param msg   消息体
     *
     * @return
     */
    @Override
    public <T> MessageSendResult syncSend(String topic, String tag, T msg) {

        topic = topic + ":" + tag;
        SendResult sendResult = this.rocketMQTemplate.syncSend(topic, msg);
        return this.messageResultConverter.convertToResult(sendResult);
    }


    /**
     * 发送异步消息
     * 发送异步消息（通过线程池执行发送到broker的消息任务，执行完后回调：在SendCallback中可处理相关成功失败时的逻辑）
     * （适合对响应时间敏感的业务场景）
     *
     * @param topic 消息Topic
     * @param msg   消息实体
     */
    @Override
    public <T> void asyncSend(String topic, T msg) {

        this.rocketMQTemplate.asyncSend(topic, msg, DefaultAsyncSendCallback.getInstance());
    }

    /**
     * 发送带tag的消息，直接在topic后面加上":tag"
     *
     * @param topic 消息主题
     * @param tag   消息tag
     * @param msg   消息体
     *
     * @return
     */
    @Override
    public <T> void asyncSend(String topic, String tag, T msg) {
        topic = topic + ":" + tag;
        this.rocketMQTemplate.asyncSend(topic, msg, DefaultAsyncSendCallback.getInstance());
    }

    /**
     * 发送异步消息
     * 发送异步消息（通过线程池执行发送到broker的消息任务，执行完后回调：在SendCallback中可处理相关成功失败时的逻辑）
     * （适合对响应时间敏感的业务场景）
     *
     * @param topic        消息Topic
     * @param message      消息实体
     * @param sendCallback 回调函数
     */
    @Override
    public <T> void asyncSend(String topic, T message, MessageSendCallback sendCallback) {

        this.rocketMQTemplate.asyncSend(topic, MessageBuilder.withPayload(message), SendCallbackWrapper.rocketMQCallbackWrapper(sendCallback));
    }

    /**
     * 发送异步消息
     * 发送异步消息（通过线程池执行发送到broker的消息任务，执行完后回调：在SendCallback中可处理相关成功失败时的逻辑）
     * （适合对响应时间敏感的业务场景）
     *
     * @param topic        消息Topic
     * @param tag          消息tag
     * @param message      消息实体
     * @param sendCallback 回调函数
     */
    @Override
    public <T> void asyncSend(String topic, String tag, T message, MessageSendCallback sendCallback) {

        topic = topic + ":" + tag;
        this.rocketMQTemplate.asyncSend(topic, message, SendCallbackWrapper.rocketMQCallbackWrapper(sendCallback));
    }

    /**
     * 发送异步消息
     *
     * @param topic        消息Topic
     * @param message      消息实体
     * @param sendCallback 回调函数
     * @param timeout      超时时间
     */
    @Override
    public <T> void asyncSend(String topic, T message, MessageSendCallback sendCallback, long timeout) {
        this.rocketMQTemplate.asyncSend(topic, message, SendCallbackWrapper.rocketMQCallbackWrapper(sendCallback), timeout);
    }

    /**
     * 发送异步消息
     *
     * @param topic        消息Topic
     * @param tag          消息tag
     * @param message      消息实体
     * @param sendCallback 回调函数
     * @param timeout      超时时间
     */
    @Override
    public <T> void asyncSend(String topic, String tag, T message, MessageSendCallback sendCallback, long timeout) {

        topic = topic + ":" + tag;
        this.rocketMQTemplate.asyncSend(topic, message, SendCallbackWrapper.rocketMQCallbackWrapper(sendCallback), timeout);
    }


    /**
     * 单向消息
     * 特点为只负责发送消息，不等待服务器回应且没有回调函数触发，即只发送请求不等待应答
     * 此方式发送消息的过程耗时非常短，一般在微秒级别
     * 应用场景：适用于某些耗时非常短，但对可靠性要求并不高的场景，例如日志收集
     *
     * @param topic 消息主题
     * @param msg   消息体
     */
    @Override
    public <T> void sendOneWayMsg(String topic, T msg) {

        this.rocketMQTemplate.sendOneWay(topic, msg);
    }


    /**
     * 单向消息
     * 特点为只负责发送消息，不等待服务器回应且没有回调函数触发，即只发送请求不等待应答
     * 此方式发送消息的过程耗时非常短，一般在微秒级别
     * 应用场景：适用于某些耗时非常短，但对可靠性要求并不高的场景，例如日志收集
     *
     * @param topic 消息主题
     * @param tag   消息tag
     * @param msg   消息体
     */
    @Override
    public <T> void sendOneWayMsg(String topic, String tag, T msg) {
        topic = topic + ":" + tag;
        this.rocketMQTemplate.sendOneWay(topic, msg);
    }

    /**
     * 仅仅返送单次消息，不会关心后续消费，重试等
     *
     * @param topic
     * @param msg
     */
    @Override
    public <T> void sendOnceMsg(String topic, T msg) {

        this.sendOneWayMsg(topic, msg);
    }


    /**
     * 仅仅返送单次消息，不会关心后续消费，重试等
     *
     * @param topic
     * @param tag
     * @param msg
     */
    @Override
    public <T> void sendOnceMsg(String topic, String tag, T msg) {

        this.sendOneWayMsg(topic, tag, msg);
    }


    /**
     * 发送批量消息
     *
     * @param topic       消息主题
     * @param messageList 消息体集合
     *
     * @return
     */
    @Override
    public <T> MessageSendResult asyncSendBatch(String topic, List<T> messageList) {

        this.rocketMQTemplate.asyncSend(topic, messageList, DefaultAsyncSendCallback.getInstance());
        return MessageSendResult.SUCCESS();
    }

    /**
     * 发送批量消息
     *
     * @param topic       消息主题
     * @param tag         消息tag
     * @param messageList 消息体集合
     *
     * @return
     */
    @Override
    public <T> MessageSendResult asyncSendBatch(String topic, String tag, List<T> messageList) {

        topic = topic + ":" + tag;
        return this.asyncSendBatch(topic, messageList);
    }


    /**
     * 同步延迟消息
     * rocketMQ的延迟消息发送其实是已发送就已经到broker端了，然后消费端会延迟收到消息。
     * RocketMQ 目前只支持固定精度的定时消息。
     * 固定等级：1到18分别对应1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     * 延迟的底层方法是用定时任务实现的。
     * 发送延时消息（delayLevel的值就为0，因为不延时）
     *
     * @param topic      消息主题
     * @param msg        消息体
     * @param timeout    发送超时时间
     * @param delayLevel 延迟级别  1到18
     */
    @Override
    public <T> void sendDelay(String topic, T msg, long timeout, int delayLevel) {

        Message<T> message = MessageBuilder.withPayload(msg).build();
        this.rocketMQTemplate.syncSend(topic, message, timeout, delayLevel);
    }

    /**
     * 发送异步延迟消息
     *
     * @param topic        消息Topic
     * @param msg          消息实体
     * @param sendCallback 回调函数
     * @param timeout      超时时间
     * @param delayLevel   延迟消息的级别
     */
    @Override
    public <T> void asyncSendDelay(String topic, T msg, MessageSendCallback sendCallback, long timeout, int delayLevel) {

        Message<T> message = MessageBuilder.withPayload(msg).build();
        this.rocketMQTemplate.asyncSend(topic, message, SendCallbackWrapper.rocketMQCallbackWrapper(sendCallback), timeout, delayLevel);
    }

    /**
     * 发送异步延迟消息
     *
     * @param topic      消息Topic
     * @param msg        消息实体
     * @param timeout    超时时间
     * @param delayLevel 延迟消息的级别
     */
    @Override
    public <T> void asyncSendDelay(String topic, T msg, long timeout, int delayLevel) {
        Message<T> message = MessageBuilder.withPayload(msg).build();
        this.rocketMQTemplate.asyncSend(topic, message, DefaultAsyncSendCallback.getInstance(), timeout, delayLevel);
    }

    /**
     * 发送顺序消息
     *
     * @param topic   消息主题
     * @param msg     消息体
     * @param hashKey 确定消息发送到哪个队列中
     */
    @Override
    public <T> void syncSendOrderly(String topic, T msg, String hashKey) {

        Message<T> message = MessageBuilder.withPayload(msg).build();
        log.info("发送顺序消息，topic:{}, hashKey:{}", topic, hashKey);
        rocketMQTemplate.syncSendOrderly(topic, message, hashKey);
    }

    /**
     * 发送顺序消息
     *
     * @param topic   消息主题
     * @param msg     消息体
     * @param hashKey 确定消息发送到哪个队列中
     * @param timeout 超时时间
     */
    @Override
    public <T> void syncSendOrderly(String topic, T msg, String hashKey, long timeout) {
        Message<T> message = MessageBuilder.withPayload(msg).build();
        log.info("发送顺序消息，topic:{}, hashKey:{}, timeout:{}", topic, hashKey, timeout);
        rocketMQTemplate.syncSendOrderly(topic, message, hashKey, timeout);
    }

    /**
     * 发送事务消息
     *
     * @param txProducerGroup 事务消息的生产者组名称
     * @param topic           事务消息主题
     * @param tag             事务消息tag
     * @param msg             事务消息体
     * @param arg             事务消息监听器回查参数
     */
    @Override
    public <T> void sendTransaction(String txProducerGroup, String topic, String tag, T msg, T arg) {

        if (StringUtils.isNotEmpty(tag)) {
            topic = topic + ":" + tag;
        }
        String transactionId = UUID.randomUUID().toString();
        Message<T> message = MessageBuilder.withPayload(msg)
                //header也有大用处
                .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId)
                .setHeader("share_id", msg.hashCode())
                .build();
        TransactionSendResult result = rocketMQTemplate.sendMessageInTransaction(topic, message, arg);
        if (result.getLocalTransactionState().equals(LocalTransactionState.COMMIT_MESSAGE)
                && result.getSendStatus().equals(SendStatus.SEND_OK)) {
            log.info("事物消息发送成功");
        }
    }

    @Override
    public <T> void sendTransaction(String topic, String tag, T msg) {
        throw new UnsupportedOperationException("RocketMQ 暂不支持此种事务消息!");
    }

    @Override
    public <T> Object sendRequest(String tag, T msg) {

        throw new UnsupportedOperationException("RocketMQ 暂不支持请求消息!");
    }
}
