package com.geek.im.server.domain.aggregate;

import geek.im.server.common.enums.DeviceTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author : HK意境
 * @ClassName : UserConnectCacheDomain
 * @date : 2024/8/7 21:00
 * @description :Redis哈希表的键可以是用户ID，值是一个包含多个设备连接信息的哈希。每个设备的连接信息可以存储在一个嵌套的哈希表中
 * <p>
 * 数据结构 user:<userID> -> {
 * device:<deviceID1> -> { instanceID: <instanceID>, connectionTime: <connectionTime> },
 * device:<deviceID2> -> { instanceID: <instanceID>, connectionTime: <connectionTime> }
 * }
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@Accessors(chain = true)
public class UserConnectCacheDomain {

    private Long userId;

    private ConnectDevice connectDevice;

    @Data
    public static class ConnectDevice {

        /**
         * 设备类型：@{@link DeviceTypeEnum}
         */
        private String deviceType;

        /**
         * 服务定位符：{@link IMServer::getServerLocation}
         */
        private String serverLocation;

        /**
         * 连接时间
         */
        private LocalDateTime connectionTime;
    }

}
