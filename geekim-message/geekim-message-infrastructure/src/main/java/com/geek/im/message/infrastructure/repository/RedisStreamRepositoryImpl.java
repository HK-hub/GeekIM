package com.geek.im.message.infrastructure.repository;

import com.geek.im.message.domain.repository.RedisStreamRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.connection.stream.Record;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : RedisStreamRepositoryImpl
 * @date : 2024/3/15 21:59
 * @description : redis Stream类型数据操作Dao接口实现
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Repository("springDataRedisStreamRepository")
public class RedisStreamRepositoryImpl<T> implements RedisStreamRepository<T> {

    private final StreamOperations<String, String, T> streamOperations;

    private final StringRedisTemplate stringRedisTemplate;

    public RedisStreamRepositoryImpl(@Autowired StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.streamOperations = stringRedisTemplate.opsForStream();

        stringRedisTemplate.execute((RedisCallback) RedisConnection::scriptingCommands);
    }


    @Override
    public StreamOperations getStreamOperations() {
        return streamOperations;
    }

    @Override
    public void initStream(String key, String group) {
        //判断key是否存在，如果不存在则创建
        boolean hasKey = true;
        if (StringUtils.isNotEmpty(key)) {
            hasKey = Boolean.TRUE.equals(stringRedisTemplate.hasKey(key));
        }
        if (BooleanUtils.isFalse(hasKey)) {
            // 如果stream 不存在
            Map<String, T> map = new HashMap<>();
            map.put("field", (T) "value");
            // 返回消息id
            String recordId = insertStreamAll(key, map);
            // 创建消费者组
            createGroup(key, group);
            //将初始化的值删除掉
            remove(key, recordId);
            log.debug("stream:{}-event:{} initialize success", key, group);
        }
    }


    @Override
    public String insertStream(String key, String field, T value) {
        Map<String, T> content = new HashMap<>(1);
        content.put(field, value);
        return insertStreamAll(key, content);
    }


    @Override
    public String insertStreamAll(String key, Map<String, T> content) {

        return streamOperations.add(key, content).getValue();
    }

    /**
     * 删除stream内的消息记录
     *
     * @param key
     * @param recordIds
     *
     * @return
     */
    @Override
    public Long remove(String key, String... recordIds) {
        return streamOperations.delete(key, recordIds);
    }


    /**
     * 创建消费者组
     *
     * @param key
     * @param group
     *
     * @return
     */
    @Override
    public String createGroup(String key, String group) {
        return streamOperations.createGroup(key, group);
    }


    /**
     * 确认消息
     *
     * @param key
     * @param group
     * @param recordIds
     *
     * @return
     */
    @Override
    public Long ack(String key, String group, String... recordIds) {
        return streamOperations.acknowledge(key, group, recordIds);
    }


    /**
     * 描述: 确认已消费
     *
     * @param group
     * @param record
     *
     * @date 2022/4/19 13:56
     */
    @Override
    public Long ack(String group, Record<String, ?> record) {
        return streamOperations.acknowledge(group, record);
    }


    /**
     * 获取stream消息队列内消息数量
     *
     * @param key
     *
     * @return
     */
    @Override
    public Long len(String key) {
        return streamOperations.size(key);
    }


    /**
     * 从头开始消费
     *
     * @param key
     *
     * @return
     */
    @Override
    public List<MapRecord<String, String, T>> readByZero(String key) {
        return streamOperations.read(StreamOffset.fromStart(key));
    }


    /**
     * 从指定消息id之后开始消费
     *
     * @param key
     * @param recordId
     *
     * @return
     */
    @Override
    public List<MapRecord<String, String, T>> readById(String key, String recordId) {
        return streamOperations.read(StreamOffset.from(MapRecord.create(key, new HashMap<>(1))
                .withId(RecordId.of(recordId))));
    }

    @Override
    public PendingMessages readWithPending(String key, Consumer consumer) {
        //从零到最大,10条数据
        // return streamOperations.pending(key, consumer, Range.closed("0", "+"), 10L);
        return streamOperations.pending(key, consumer);
    }


    @Override
    public PendingMessagesSummary readWithPending(String key, String group) {
        return streamOperations.pending(key, group);
    }

    /**
     * 获取消费者信息
     *
     * @param key
     * @param group
     *
     * @return
     */
    @Override
    public StreamInfo.XInfoConsumers getConsumers(String key, String group) {

        return streamOperations.consumers(key, group);
    }

    @Override
    public List<MapRecord<String, String, T>> getMsgList(String key, Range<String> rightOpen) {
        return streamOperations.range(key, rightOpen);
    }

    @Override
    public List<MapRecord<String, String, T>> range(String key, RecordId startId, RecordId endId, Integer count) {

        return streamOperations.range(key,
                Range.from(Range.Bound.exclusive(startId.getValue())).to(Range.Bound.exclusive(startId.getValue())),
                RedisZSetCommands.Limit.limit().count(count));
    }

    @Override
    public StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }
}

