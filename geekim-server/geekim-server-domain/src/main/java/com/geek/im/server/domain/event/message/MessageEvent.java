package com.geek.im.server.domain.event.message;

import com.geek.im.server.domain.entity.message.BaseMessage;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : MessageEvent
 * @date : 2024/10/27 22:04
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@Accessors(chain = true)
public class MessageEvent {

    /**
     * 消息头
     */
    private Map<String, Object> headers;

    /**
     * 会话id
     */
    private String sessionId;

    /**
     * 会话类型
     */
    private Integer sessionType;

    /**
     * 通信类型
     */
    private Integer communicationType;

    /**
     * 消息基类
     */
    private BaseMessage baseMessage;
}
