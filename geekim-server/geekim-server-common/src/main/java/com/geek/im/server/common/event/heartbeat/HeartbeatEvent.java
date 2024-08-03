package com.geek.im.server.common.event.heartbeat;

import com.geek.im.server.common.enums.IMServerEventEnum;
import com.geek.im.server.common.event.AbstractServerEvent;
import lombok.Data;

/**
 * @author : HK意境
 * @ClassName : HeartbeatEvent
 * @date : 2024/4/29 20:15
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
public class HeartbeatEvent extends AbstractServerEvent<String> {


    /**
     * 心跳事件
     *
     * @return
     */
    @Override
    public Integer getEventType() {

        return IMServerEventEnum.HEARTBEAT.getCode();
    }
}
