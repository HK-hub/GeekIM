package com.geek.im.message.infrastructure.handler;

import com.geek.im.message.domain.repository.RedisStreamRepository;
import com.geek.im.message.domain.service.MsgRecordQueueService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.redis.connection.RedisStreamCommands.XClaimOptions.minIdle;

/**
 * @ClassName DeadLetter
 * @Description 处理死信问题
 * @Author wk
 **/
@Slf4j
@Component
public class DeadLetter {

    private DeadLetter() {
    }

    private static DeadLetter deadLetter;

    static {
        deadLetter = new DeadLetter();
    }

    public static DeadLetter getInstance() {
        return deadLetter;
    }

    @Resource
    private RedisStreamRepository<String> redisStreamRepository;

    @Resource
    private MsgRecordQueueService msgRecordQueueService;

    /**
     * 描述: 生成等待转组的数据
     *
     * @param pendingMessagesSummary
     *
     * @return java.util.Map<java.lang.String, java.util.List < org.springframework.data.redis.connection.stream.RecordId>>
     *
     * @date 2022/4/19 16:37
     */
    public Map<String, List<RecordId>> waitChangeConsumerMap(PendingMessagesSummary pendingMessagesSummary, String key) {

        //消费者组名称
        String groupName = pendingMessagesSummary.getGroupName();
        //pending队列中最小id
        String minMessageId = pendingMessagesSummary.minMessageId();
        //pending队列中最大id
        String maxMessageId = pendingMessagesSummary.maxMessageId();
        //获取每个消费者的pending消息数量
        Map<String, Long> pendingMessagesPerConsumer = pendingMessagesSummary.getPendingMessagesPerConsumer();
        //待转组的消息
        Map<String, List<RecordId>> consumerRecordIdMap = new HashMap<>();
        //遍历每个消费者pending消息
        pendingMessagesPerConsumer.entrySet().forEach(entry -> {
            //带转组的recordId
            List<RecordId> list = new ArrayList<>();
            //消费者
            String consumer = entry.getKey();
            Long consumerPendingMessages = entry.getValue();
            log.info("消费者:{},一共有{}条pending消息", consumer, consumerPendingMessages);
            if (consumerPendingMessages > 0) {
                //读取消费者pending队列前10 条记录,从id = 0的记录开始,一直到最大值
                PendingMessages pendingMessages = redisStreamRepository.readWithPending(key, Consumer.from(groupName, consumer));
                //遍历pending详情
                pendingMessages.forEach(message -> {
                    //消息的id
                    RecordId recordId = message.getId();
                    //消息已读时长(消息从消费组中获取,到此刻的时间)
                    Duration elapsedTimeSinceLastDelivery = message.getElapsedTimeSinceLastDelivery();
                    //消息被读取次数(消息被获取的次数)
                    long deliveryCount = message.getTotalDeliveryCount();
                    //判断是否是超过60 秒没有消费
                    if (elapsedTimeSinceLastDelivery.getSeconds() > 60) {
                        //判断消息被读取次数是否为 1次
                        if (1 == deliveryCount) {
                            //进行转组
                            list.add(recordId);
                        } else {
                            //手动确认并记录异常
                            log.info("手动ACK消息,并记录异常,id={},elapsedTimeSinceLastDelivery={},deliveryCount{}", recordId, elapsedTimeSinceLastDelivery, deliveryCount);
                            msgRecordQueueService.saveErrorMsgRecord(key, recordId);
                            redisStreamRepository.ack(key, groupName, recordId.getValue());
                        }
                    }
                });
                if (list.size() > 0) {
                    consumerRecordIdMap.put(consumer, list);
                }
            }
        });

        return consumerRecordIdMap;

    }

    /**
     * 描述: 对消息进行转组
     *
     * @param consumerRecordIdMap
     *
     * @return void
     *
     * @date 2022/4/19 16:12
     */
    public void changeConsumer(Map<String, List<RecordId>> consumerRecordIdMap, String key, String group) {
        consumerRecordIdMap.entrySet().forEach(entry -> {
            //根据当前consumer获取新的consumer  命令 XINFO CONSUMERS mystream mygroup
            String oldConsumer = entry.getKey();
            StreamInfo.XInfoConsumers consumers = redisStreamRepository.getConsumers(key, group);
            if (consumers.size() < 0) {
                log.info("转组失败：{}组没有消费者", group);
                handleFailureMsg(key, group, entry.getValue());
                return;
            }
            String[] newConsumer = {""};

            for (int i = 0; i < consumers.size(); i++) {
                if (!oldConsumer.equals(consumers.get(i).consumerName())) {
                    newConsumer[0] = consumers.get(i).consumerName();
                    break;
                }
            }
            if (newConsumer[0].equals("")) {
                log.info("转组失败：{}组没有其他消费者", group);
                handleFailureMsg(key, group, entry.getValue());
                return;
            }
            List<RecordId> recordIds = entry.getValue();
            //转组
            List<ByteRecord> retVal = (List<ByteRecord>) redisStreamRepository.getStringRedisTemplate().execute(new RedisCallback<List<ByteRecord>>() {
                @Override
                public List<ByteRecord> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    // 相当于执行XCLAIM操作，批量将某一个consumer中的消息转到另外一个consumer中
                    return redisConnection.streamCommands().xClaim(key.getBytes(),
                            group, newConsumer[0], minIdle(Duration.ofSeconds(10)).ids(recordIds));
                }

            });
            if (retVal.size() > 0) {
                for (ByteRecord byteRecord : retVal) {
                    log.info("改了消息的消费者：id={}, value={},newConsumer={}", byteRecord.getId(), byteRecord.getValue(), newConsumer[0]);
                }
            }

        });

    }

    /**
     * 描述:处理转组失败的消息，手动ack
     *
     * @param
     *
     * @return
     *
     * @author wangke
     * @date 2022/4/22 17:36
     */
    private void handleFailureMsg(String key, String group, List<RecordId> recordIds) {

        for (RecordId recordId : recordIds) {
            //记录并ACK
            msgRecordQueueService.saveErrorMsgRecord(key, recordId);
            redisStreamRepository.ack(key, group, recordId.getValue());
        }
    }

}

