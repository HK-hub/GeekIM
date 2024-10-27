package com.geek.im.server.domain.entity.message;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : HK意境
 * @ClassName : BaseMessage
 * @date : 2024/8/28 20:18
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
@Setter
public abstract class BaseMessage {

    /**
     * 消息元数据
     */
    private MessageMeta messageMeta;

    /**
     * 消息类型: {@link geek.im.server.common.enums.MessageTypeEnum}
     */
    protected Integer messageType;

    /**
     * 消息id
     */
    protected Long messageId;

    /**
     * 消息序列号
     */
    protected Long sequence;

    /**
     * 发送者id
     */
    protected Long senderId;

    /**
     * 接收者id
     */
    protected Long receiverId;

    /**
     * 群组id
     */
    private Long groupId;

    /**
     * 聊天类型：{@link geek.im.server.common.enums.ChatTypeEnum}
     */
    protected Integer chatType;

    /**
     * 状态
     */
    protected Integer status;

    /**
     * 消息内容: 简单消息内容
     */
    protected String content;
}
