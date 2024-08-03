package com.geek.im.server.common.event;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author : HK意境
 * @ClassName : AbstractServerEvent
 * @date : 2024/4/27 20:12
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
@Setter
public abstract class AbstractServerEvent<T> implements IServerEvent, Serializable {

    private long sequenceId;

    private int size;

    private Integer eventType;

    private byte[] bytes;

    private String serialStrategy;

    private T body;

    @Override
    public long getSequenceId() {
        return this.sequenceId;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    /**
     * 获取服务器事件类型
     *
     * @return
     */
    @Override
    public Integer getEventType() {
        return this.eventType;
    }

    /**
     * 获取负载byte数组
     *
     * @return
     */
    @Override
    public byte[] getBytes() {

        return this.bytes;
    }

    /**
     * 获取负载
     *
     * @return
     */
    @Override
    public T getPayload() {
        return this.body;
    }


    /**
     * 获取内容文本
     *
     * @return
     */
    @Override
    public String getContentText() {
        return body.toString();
    }


    @Override
    public String toString() {
        return this.getContentText();
    }

    /**
     * 获取序列化协议
     *
     * @return
     */
    @Override
    public String serialStrategy() {
        return this.serialStrategy;
    }
}
