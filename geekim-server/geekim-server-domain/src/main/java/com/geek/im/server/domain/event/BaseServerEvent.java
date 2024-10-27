package com.geek.im.server.domain.event;

import geek.im.server.common.enums.ServerEventEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author : HK意境
 * @ClassName : BaseServerEvent
 * @date : 2024/4/27 20:12
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
@Setter
public class BaseServerEvent implements IServerEvent, Serializable {

    /**
     * 事件类型: {@link ServerEventEnum}
     */
    protected Integer eventType;

    /**
     * 负载数据:
     */
    protected String data;

    protected ServerEventEnum serverEventEnum;

    /**
     * 获取服务器事件类型
     *
     * @return
     */
    @Override
    public Integer getEventType() {
        return this.eventType;
    }

    public void setEventType(Integer eventType) {

        this.eventType = eventType;
        this.serverEventEnum = ServerEventEnum.of(this.eventType);
    }
}
