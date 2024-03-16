package com.geek.im.message.domain.repository;

import org.springframework.data.domain.Range;
import org.springframework.data.redis.connection.stream.Record;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : RedisStreamRepository
 * @date : 2024/3/15 21:58
 * @description : redis Stream类型数据操作Dao接口
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface RedisStreamRepository<T> {

    /**
     * 描述: 获取当前操作 StreamOperations
     *
     * @param
     *
     * @return org.springframework.data.redis.core.StreamOperations
     *
     * @author wangke
     * @date 2022/4/15 9:44
     */
    StreamOperations getStreamOperations();

    /**
     * 描述: 初始化stream和消费者组
     *
     * @param key   关键字
     * @param group 消费者组
     *
     * @return void
     *
     * @author wangke
     * @date 2022/4/15 15:13
     */
    void initStream(String key, String group);

    /**
     * 描述: 添加消息到末尾
     *
     * @param key
     * @param field
     * @param value
     *
     * @return java.lang.String
     *
     * @author wangke
     * @date 2022/4/15 10:20
     */
    String insertStream(String key, String field, T value);

    /**
     * 描述: 批量添加消息到末尾
     *
     * @param key
     * @param content
     *
     * @return java.lang.String
     */
    String insertStreamAll(String key, Map<String, T> content);

    /**
     * 描述: 删除消息，这里的删除仅仅是设置了标志位，不影响消息总长度
     * 消息存储在stream的节点下，删除时仅对消息做删除标记，当一个节点下的所有条目都被标记为删除时，销毁节点
     *
     * @param key
     * @param recordIds
     *
     * @return java.lang.Long
     *
     * @date 2022/4/15 10:24
     */
    Long remove(String key, String... recordIds);

    /**
     * 描述: 创建消费组
     *
     * @param key
     * @param group
     *
     * @return java.lang.String
     *
     * @date 2022/4/19 13:56
     */
    String createGroup(String key, String group);

    /**
     * 描述: 确认已消费
     *
     * @param key
     * @param group
     * @param recordIds
     *
     * @return java.lang.Long
     *
     * @date 2022/4/19 13:56
     */
    Long ack(String key, String group, String... recordIds);

    /**
     * 描述: 确认已消费
     *
     * @param group
     * @param record
     *
     * @return java.lang.Long
     *
     * @date 2022/4/19 13:56
     */
    Long ack(String group, Record<String, ?> record);


    /**
     * 消息长度
     *
     * @param key
     *
     * @return
     */
    Long len(String key);

    /**
     * 从头开始读
     *
     * @param key
     *
     * @return MapRecord<String, String, T> <stream,field,value>
     *
     * @date 2022/4/19 13:56
     */
    List<MapRecord<String, String, T>> readByZero(String key);

    /**
     * 从指定的ID开始读
     *
     * @param key
     * @param recordId
     *
     * @return MapRecord<String, String, T> <stream,field,value>
     *
     * @date 2022/4/19 13:56
     */
    List<MapRecord<String, String, T>> readById(String key, String recordId);

    /**
     * 读取pending 中未处理的数据
     *
     * @param key      stream的Key
     * @param consumer 消费者信息
     *
     * @return org.springframework.data.redis.connection.stream.PendingMessages
     *
     * @date 2022/4/19 13:54
     */
    PendingMessages readWithPending(String key, Consumer consumer);

    /**
     * 描述: 读取pending 中未处理的数据
     *
     * @param key   stream的Key
     * @param group 消费者组
     *
     * @return org.springframework.data.redis.connection.stream.PendingMessagesSummary
     *
     * @date 2022/4/19 13:52
     */
    PendingMessagesSummary readWithPending(String key, String group);

    /**
     * 描述: 获取消费组信息
     *
     * @param key
     * @param group
     *
     * @return org.springframework.data.redis.connection.stream.StreamInfo.XInfoConsumers
     *
     * @date 2022/4/19 17:19
     */
    StreamInfo.XInfoConsumers getConsumers(String key, String group);

    /**
     * 描述: 获取消息列表，会自动过滤已经删除的消息
     *
     * @param key
     * @param rightOpen
     *
     * @return java.util.List<org.springframework.data.redis.connection.stream.MapRecord < java.lang.String, java.lang.String, T>>
     *
     * @date 2022/4/20 14:42
     */
    List<MapRecord<String, String, T>> getMsgList(String key, Range<String> rightOpen);

    /**
     * 描述: 从特定范围内的流中读取记录
     *
     * @param key
     * @param startId
     * @param endId
     * @param count
     *
     * @return java.util.List<org.springframework.data.redis.connection.stream.MapRecord < java.lang.String, java.lang.String, T>>
     *
     * @date 2022/4/24 14:45
     */
    List<MapRecord<String, String, T>> range(String key, RecordId startId, RecordId endId, Integer count);

    StringRedisTemplate getStringRedisTemplate();
}


