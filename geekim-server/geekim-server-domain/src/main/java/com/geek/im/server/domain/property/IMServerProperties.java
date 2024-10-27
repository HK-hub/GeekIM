package com.geek.im.server.domain.property;

import geek.im.server.common.enums.EncryptAlgorithmEnum;
import geek.im.server.common.enums.GroupSyncTypeEnum;
import geek.im.server.common.protocol.ConnectProtocol;
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

    /**
     * 服务器基础配置
     */
    private ServerProperty server = new ServerProperty();

    /**
     * 线程组配置
     */
    private EventGroupProperty event = new EventGroupProperty();

    /**
     * 安全配置
     */
    private SecurityProperty security = new SecurityProperty();

    /**
     * 用户行为配置
     */
    private UserBehaviorProperty behavior = new UserBehaviorProperty();

    /**
     * 群组配置
     */
    private GroupProperty group = new GroupProperty();

    public String buildServerLocation() {
        return server.getHost() + ":" + server.getPort();
    }


    /**
     * 服务器参数配置
     */
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
        private Integer maxFrameLength = 65535;

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

    /**
     * 线程配置
     */
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


    @Data
    public static class SecurityProperty {

        /**
         * 响应返回消息的时候是否进行Base64编码
         */
        private Boolean base64ed = true;

        /**
         * 事件加密算法
         */
        private EncryptAlgorithmEnum encryptAlgorithm = EncryptAlgorithmEnum.NONE;
    }


    /**
     * 用户行为配置
     */
    @Getter
    @Setter
    @ToString
    public static class UserBehaviorProperty {

        /**
         * 是否允许多端登录
         */
        private Boolean allowMultiTerminalLogin = true;

        /**
         * 是否允许异地登录(同一设备类型下多地登录)
         */
        private Boolean allowMultiPlaceLogin = false;

        /**
         * 多端登录消息同步策略: true:多端消息同步，例如手机端发送的消息，电脑端也会显示；电脑端接受的消息，手机端也会显示；
         * false: 多端设备只接收展示本端数据
         */
        private Boolean allowMultiSync = true;
    }

    /**
     * 群组相关配置
     */
    @Getter
    @Setter
    @ToString
    public static class GroupProperty {

        /**
         * 群聊人数限制:默认Integer.MAX
         */
        private int groupMembersLimit = Integer.MAX_VALUE;

        /**
         * 大群人数临界点：满足X人即为大群，大群消息同步策略有所不同
         */
        private int bigGroupStartNum = 1000;

        /**
         * 群聊消息同步策略
         */
        private GroupSyncTypeEnum groupSyncType = GroupSyncTypeEnum.index;
    }


}
