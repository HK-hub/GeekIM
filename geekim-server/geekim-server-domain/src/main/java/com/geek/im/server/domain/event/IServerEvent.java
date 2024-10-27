package com.geek.im.server.domain.event;

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

    /**
     * 获取服务器事件类型
     *
     * @return
     */
    public Integer getEventType();

    /**
     * 获取负载数据
     */
    public String getData();

}
