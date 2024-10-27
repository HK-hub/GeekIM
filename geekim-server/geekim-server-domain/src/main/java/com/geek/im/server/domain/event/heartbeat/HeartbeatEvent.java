package com.geek.im.server.domain.event.heartbeat;

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
public class HeartbeatEvent {

    private Long lastReportTime;

}
