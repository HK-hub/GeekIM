package com.geek.im.server.common.event.message;

import com.geek.im.server.common.enums.IMServerEventEnum;
import com.geek.im.server.common.event.AbstractServerEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : HK意境
 * @ClassName : AbstractMessageEvent
 * @date : 2024/4/29 20:18
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AbstractMessageEvent extends AbstractServerEvent<String> {

    /**
     * 消息类型
     */
    protected Integer messageType;


    private Long messageId;

    /**
     * 消息序列
     */
    private Long messageSequence;

    private Long senderId;

    private Long receiverId;

    /**
     * 聊天类型：1.私聊，2.群聊
     */
    private Integer chatType;

    /**
     * 状态
     */
    private Integer status;


    /**
     * 消息内容: 简单消息内容
     */
    protected String content;


    /**
     * 获取服务器事件类型
     *
     * @return
     */
    @Override
    public Integer getEventType() {
        return IMServerEventEnum.MESSAGE.getCode();
    }
}
