package com.geek.im.message.application.service;

import com.geek.im.message.domain.service.MsgDataQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : MsgDataQueueServiceImpl
 * @date : 2024/3/16 21:34
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Service
public class MsgDataQueueServiceImpl implements MsgDataQueueService {
    /**
     * 描述: 添加消息到date_stream
     *
     * @param value
     *
     * @return void
     *
     * @author wangke
     * @date 2022/4/21 16:19
     */
    @Override
    public void sendMsg(Map<String, String> value) {

    }

    /**
     * 描述: 添加消息到date_stream
     *
     * @param model msgConfigKey,msgContent,msgCreateUser,msgSendUser,sendDingDing
     *
     * @return void
     *
     * @throws Exception 创建消息参数缺失,未配置消息模板
     * @author wangke
     * @date 2022/4/21 16:19
     */
    @Override
    public void sendMsg(Object model) {

    }
}
