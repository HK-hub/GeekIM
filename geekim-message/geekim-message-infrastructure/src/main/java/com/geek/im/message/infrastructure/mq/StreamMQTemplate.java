package com.geek.im.message.infrastructure.mq;

import com.geek.im.support.domain.callback.MessageSendCallback;
import com.geek.im.support.domain.dto.MessageSendResult;
import com.geek.im.support.infrastructure.message.MessageQueueTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : HK意境
 * @ClassName : StreamMQTemplate
 * @date : 2024/3/10 19:05
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "geek.im.message.using.type", havingValue = "stream")
public class StreamMQTemplate implements MessageQueueTemplate {
    /**
     * 发送消息
     *
     * @param topic
     * @param msg
     */
    @Override
    public <T> void sendMessage(String topic, T msg) {

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
        return null;
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
        return null;
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

    }

    /**
     * 仅仅返送单次消息，不会关心后续消费，重试等
     *
     * @param topic
     * @param msg
     */
    @Override
    public <T> void sendOnceMsg(String topic, T msg) {

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
        return null;
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
        return null;
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

    }

    /**
     * 发送异步延迟消息
     *
     * @param topic        消息Topic
     * @param message      消息实体
     * @param sendCallback 回调函数
     * @param timeout      超时时间
     * @param delayLevel   延迟消息的级别
     */
    @Override
    public <T> void asyncSendDelay(String topic, T message, MessageSendCallback sendCallback, long timeout, int delayLevel) {

    }

    /**
     * 发送异步延迟消息
     *
     * @param topic      消息Topic
     * @param message    消息实体
     * @param timeout    超时时间
     * @param delayLevel 延迟消息的级别
     */
    @Override
    public <T> void asyncSendDelay(String topic, T message, long timeout, int delayLevel) {

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

    }

    @Override
    public <T> Object sendRequest(String tag, T msg) {

        // 使用 redis key 监听事件
        return null;
    }
}
