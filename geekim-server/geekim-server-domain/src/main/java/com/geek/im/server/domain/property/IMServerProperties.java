package com.geek.im.server.domain.property;

import com.geek.im.server.common.protocol.connect.ConnectProtocol;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : HK意境
 * @ClassName : IMServerProperties
 * @date : 2024/7/28 21:11
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "geek.im")
public class IMServerProperties {

    private ServerProperty server;

    /**
     * 线程组配置
     */
    private EventGroupProperty group = new EventGroupProperty();

    @Getter
    @Setter
    @ToString
    public static class ServerProperty {

        /**
         * IM服务端口
         */
        private Integer port = 6666;

        /**
         * 主机地址
         */
        private String host = "localhost";

        /**
         * 服务路径
         */
        private String path = "/m";

        /**
         * 连接协议
         */
        private String protocol = ConnectProtocol.WEBSOCKET.getCode();


        /**
         * 最大数据帧长度
         */
        private Long maxFrameLength = 65535L;

        /**
         * 最大内存大小
         */
        private Integer maxContentLength = 10485760;

        /**
         * 最大等待时间
         */
        private Long maxWaitTime = 10000L;


        // 心跳保活时间。单位s : 默认3小时=3600 * 3 = 10800
        private Long maxIdleTime = 10800L;
    }

    @Getter
    @Setter
    @ToString
    public static class EventGroupProperty {

        private int boss = 2;

        private int worker = Runtime.getRuntime().availableProcessors();

        /**
         * 非阻塞IO模型模式
         */
        private boolean enableEpoll;
    }


}
