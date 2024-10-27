package com.geek.im.server.domain.entity.message;

import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : MessageMeta
 * @date : 2024/10/27 21:58
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class MessageMeta {

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

}
