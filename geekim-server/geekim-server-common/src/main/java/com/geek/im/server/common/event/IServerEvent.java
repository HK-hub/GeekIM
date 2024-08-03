package com.geek.im.server.common.event;

import java.io.Serializable;

/**
 * @author : HK意境
 * @ClassName : IServerEvent
 * @date : 2024/4/27 20:08
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface IServerEvent extends Serializable {

    public long getSequenceId();

    public long getSize();

    /**
     * 获取服务器事件类型
     *
     * @return
     */
    public Integer getEventType();


    /**
     * 获取负载byte数组
     *
     * @return
     */
    public byte[] getBytes();

    /**
     * 获取负载
     *
     * @param <T>
     *
     * @return
     */
    public <T> T getPayload();

    /**
     * 获取内容文本
     *
     * @return
     */
    public String getContentText();


    /**
     * 获取序列化协议
     *
     * @return
     */
    public String serialStrategy();

}
