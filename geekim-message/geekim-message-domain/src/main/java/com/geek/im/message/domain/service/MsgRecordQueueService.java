package com.geek.im.message.domain.service;

import org.springframework.data.redis.connection.stream.RecordId;

import java.util.Map;

/**
 * @InterfaceName MsgRecordQueueService
 * @Description 消息记录队列服务类--发送后的消息
 * @DATE 2022/4/21 10:15
 **/
public interface MsgRecordQueueService {

    /**
     * 描述: 保存消息记录
     *
     * @param value 消息
     *
     * @return 记录成功返回true
     *
     * @throws Exception 分离用户id出错
     * @author wangke
     * @date 2022/4/22 8:37
     */
    Boolean saveMsgRecord(Map<String, String> value);

    /**
     * 描述: 记录发送失败的消息
     *
     * @param key      stream_key
     * @param recordId stream_id
     *
     * @return void
     *
     * @author wangke
     * @date 2022/4/24 14:38
     */
    void saveErrorMsgRecord(String key, RecordId recordId);
}
