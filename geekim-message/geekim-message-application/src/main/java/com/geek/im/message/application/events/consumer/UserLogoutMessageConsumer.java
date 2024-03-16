package com.geek.im.message.application.events.consumer;

import com.geek.im.message.domain.repository.RedisStreamRepository;
import com.geek.im.message.domain.service.MsgParseQueueService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @author : HK意境
 * @ClassName : UserLogoutMessageConsumer
 * @date : 2024/3/15 21:39
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
public class UserLogoutMessageConsumer implements StreamListener<String, MapRecord<String, String, String>> {

    @Resource
    private MsgParseQueueService msgParseQueueService;
    @Resource
    private RedisStreamRepository<String> redisStreamRepository;


    @Override
    public void onMessage(MapRecord<String, String, String> entries) {

        log.info("接受到来自redis的消息,message_id={},stream={},body={}", entries.getId(), entries.getStream(), entries.getValue());

        //解析数据,推送到消息数据队列
        Boolean parseStatus = null;
        try {
            parseStatus = msgParseQueueService.parseMsgData(entries.getValue());
        } catch (Exception e) {

        }
        if (parseStatus) {
            // 消费完成后手动确认消费ACK
            redisStreamRepository.ack(entries.getStream(), "consumer-group", entries.getId().getValue());
        }
    }
}
