package com.geek.im.message.bootstrap.config;

import com.geek.im.message.application.events.consumer.UserLogoutMessageConsumer;
import com.geek.im.message.domain.repository.RedisStreamRepository;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import java.time.Duration;

/**
 * @author : HK意境
 * @ClassName : StreamConfig
 * @date : 2024/3/15 21:44
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
// @Component
public class StreamConfig {

    @Resource
    private RedisStreamRepository<String> redisStreamRepository;
    @Resource
    private UserLogoutMessageConsumer userLogoutMessageConsumer;


    /**
     * 构建Stream队列, 并且注册消费者
     *
     * @param key          topic主题
     * @param group        生产者组
     * @param consumerName 消费者名称
     *
     * @return
     */
    private StreamMessageListenerContainer.ConsumerStreamReadRequest<String> Construct(String key, String group, String consumerName) {
        //初始化stream和group
        redisStreamRepository.initStream(key, group);
        //指定消费最新消息
        StreamOffset<String> offset = StreamOffset.create(key, ReadOffset.lastConsumed());
        //创建消费者
        Consumer consumer = Consumer.from(group, consumerName);

        return StreamMessageListenerContainer.StreamReadRequest
                .builder(offset)
                .errorHandler((error) -> {
                })
                .cancelOnError(e -> false)
                .consumer(consumer)
                .autoAcknowledge(false) //不自动ACK确认
                .build();
    }


    /**
     * 描述: 解析消息队列 的订阅者1
     *
     * @param
     *
     * @return org.springframework.data.redis.stream.Subscription
     *
     * @date 2022/4/15 22:27
     */
    @Bean
    public Subscription subscriptionWithParseMsg(RedisConnectionFactory factory) {

        // 创建容器
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, MapRecord<String, String, String>> options = StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ofSeconds(5))
                .build();

        StreamMessageListenerContainer<String, MapRecord<String, String, String>> listenerContainer = StreamMessageListenerContainer.create(factory, options);
        // 构建流读取请求:消费者
        StreamMessageListenerContainer.ConsumerStreamReadRequest<String> consumer = this.Construct("message-stream", "message-producer-group", "sample-text-message-group");

        // 将监听类绑定到相应的stream流上
        Subscription subscription = listenerContainer.register(consumer, userLogoutMessageConsumer);
        // 启动监听
        listenerContainer.start();

        return subscription;
    }


}
