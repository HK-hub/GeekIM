package com.geek.im.message.infrastructure.handler;

import com.geek.im.message.domain.repository.RedisStreamRepository;
import com.geek.im.message.domain.service.MsgDataQueueService;
import com.geek.im.message.domain.service.MsgParseQueueService;
import com.geek.im.message.domain.service.MsgRecordQueueService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.PendingMessages;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * @ClassName hanld
 * @Description 处理
 * @DATE 2022/4/20 13:56
 **/
@Slf4j
@Component
public class HandleDeadLetter {

    private HandleDeadLetter() {
    }

    private static HandleDeadLetter handleDeadLetter;

    static {
        handleDeadLetter = new HandleDeadLetter();
    }

    public static HandleDeadLetter getInstance() {
        return handleDeadLetter;
    }

    @Resource
    private RedisStreamRepository<String> redisStreamRepository;
    @Resource
    private MsgParseQueueService msgParseQueueService;
    @Resource
    private MsgDataQueueService msgDataQueueService;
    @Resource
    private MsgRecordQueueService msgRecordQueueService;


    /**
     * 描述:  消费消息
     * 主要消费那些转组过来的消息，如果转组次数大于1，则进行尝试消费
     *
     * @param pendingMessages
     *
     * @return void
     *
     * @date 2022/4/20 14:06
     */
    public void consumptionMsg(PendingMessages pendingMessages, String key) {
        if (pendingMessages.size() > 0) {

            pendingMessages.forEach(pendingMessage -> {
                // 最后一次消费到现在的间隔
                Duration elapsedTimeSinceLastDelivery = pendingMessage.getElapsedTimeSinceLastDelivery();

                String groupName = pendingMessage.getGroupName();
                String consumerName = pendingMessage.getConsumerName();
                // 转组次数
                long totalDeliveryCount = pendingMessage.getTotalDeliveryCount();
                // 只消费转组次数大于1次的
                if (totalDeliveryCount > 1) {
                    try {
                        RecordId id = pendingMessage.getId();
                        //获取消息列表，会自动过滤已经删除的消息
                        List<MapRecord<String, String, String>> result = redisStreamRepository.getMsgList(key, Range.rightOpen(id.toString(), id.toString()));
                        MapRecord<String, String, String> entries = result.get(0);
                        // 消费消息
                        log.info("获取到转组的消息，消费了该消息id={}, 消息value={}, 消费者={}", entries.getId(), entries.getValue(), consumerName);
                        //处理业务
                        this.handleBusiness(key, entries.getValue());
                        // 手动ack消息
                        redisStreamRepository.ack(groupName, entries);
                    } catch (Exception e) {
                        // 异常处理
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    /**
     * 描述: 处理业务
     *
     * @param key
     * @param value
     *
     * @return void
     *
     * @date 2022/4/20 17:35
     */
    private void handleBusiness(String key, Map<String, String> value) {
        //根据key的不同选择不同的业务进行处理，同监听类中的业务处理方法
        /*switch (key) {
            case RedisStreamConstants.MSG_PARSE_STREAM:
                msgParseQueueService.saveMsgData(value);
                redisStreamService.insertStreamAll(RedisStreamConstants.MSG_DATA_STREAM, value);
                break;
            case RedisStreamConstants.MSG_DATA_STREAM:
                msgDataQueueService.sendMsg(value);
                redisStreamService.insertStreamAll(RedisStreamConstants.MSG_RECORD_STREAM, value);
                break;
            case RedisStreamConstants.MSG_RECORD_STREAM:
                msgRecordQueueService.saveMsgRecord(value);
                break;
            default:
                break;
        }*/

    }

}

