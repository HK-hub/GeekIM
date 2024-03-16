package com.geek.im.message.application.service;

import com.geek.im.message.domain.service.MsgParseQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : MsgParseQueueServiceImpl
 * @date : 2024/3/16 21:32
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Service
public class MsgParseQueueServiceImpl implements MsgParseQueueService {
    /**
     * 描述: 添加消息到parse_stream
     *
     * @param configKey config的key
     *
     * @return void
     *
     * @author wangke
     * @date 2022/4/21 16:19
     */
    @Override
    public void saveMsgData(String configKey) {

    }

    /**
     * 描述: 添加消息到parse_stream
     *
     * @param value
     *
     * @return void
     *
     * @author wangke
     * @date 2022/4/21 16:19
     */
    @Override
    public void saveMsgData(Map<String, String> value) {

    }

    /**
     * 描述: 解析数据，根据模板生成消息
     *
     * @param value
     *
     * @return 解析成功返回true
     *
     * @throws Exception 反射获取方法失败
     * @author wangke
     * @date 2022/4/21 13:53
     */
    @Override
    public Boolean parseMsgData(Map<String, String> value) throws Exception {
        return null;
    }
}
