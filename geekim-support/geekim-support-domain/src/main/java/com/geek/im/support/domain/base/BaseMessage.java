package com.geek.im.support.domain.base;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author : HK意境
 * @ClassName : BaseMessage
 * @date : 2024/3/9 19:22
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
@Setter
public class BaseMessage {

    /**
     * 业务键
     */
    protected String key;
    /**
     * 发送消息来源，用于排查问题
     */
    protected String source = "";

    /**
     * 发送时间
     */
    protected LocalDateTime sendTime = LocalDateTime.now();

    /**
     * 重试次数，用于判断重试次数，超过重试次数发送异常警告
     */
    protected Integer retryTimes = 0;

}
