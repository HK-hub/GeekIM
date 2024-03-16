package com.geek.im.message.application.service;

import com.geek.im.message.domain.service.MsgRecordQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : MsgRecordQueueServiceImpl
 * @date : 2024/3/16 21:31
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Service
public class MsgRecordQueueServiceImpl implements MsgRecordQueueService {
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
    @Override
    public Boolean saveMsgRecord(Map<String, String> value) {
        return null;
    }

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
    @Override
    public void saveErrorMsgRecord(String key, RecordId recordId) {

    }
}
