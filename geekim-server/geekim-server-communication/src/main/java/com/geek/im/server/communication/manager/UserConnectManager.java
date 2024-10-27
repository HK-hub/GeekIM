package com.geek.im.server.communication.manager;

import com.geek.im.server.domain.aggregate.UserConnectCacheDomain;
import com.geek.im.server.domain.property.IMServerProperties;
import com.geek.im.server.domain.service.UserConnectedDomainCacheService;
import com.geek.im.server.domain.service.UserOnlineOfflineService;
import com.geek.im.server.domain.value.ClientConnectRequest;
import com.geek.im.server.infrastructure.manager.UserLocalChannelManager;
import geek.im.server.common.constants.ChannelContextAttributeConstants;
import geek.im.server.common.enums.DeviceTypeEnum;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author : HK意境
 * @ClassName : UserConnectManager
 * @date : 2024/8/15 14:55
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
public class UserConnectManager {

    @Resource
    private UserLocalChannelManager userLocalChannelManager;

    @Resource
    private UserConnectedDomainCacheService userConnectedDomainCacheService;

    @Resource
    private UserOnlineOfflineService userOnlineOfflineService;


    /**
     * 连接服务器成功
     *
     * @param userId
     * @param channel
     *
     * @return
     */
    public boolean connect(Long userId, Channel channel) {

        this.userLocalChannelManager.addChannel(userId, channel);

        // 获取附件信息
        ClientConnectRequest connectRequest = (ClientConnectRequest) channel.attr(AttributeKey.valueOf(ChannelContextAttributeConstants.connectRequestInfo)).get();
        IMServerProperties serverProperties = (IMServerProperties) channel.attr(AttributeKey.valueOf(ChannelContextAttributeConstants.serverProperties)).get();

        // 设置进入分布式缓存中
        UserConnectCacheDomain connectDomain = new UserConnectCacheDomain();
        UserConnectCacheDomain.ConnectDevice connectDevice = new UserConnectCacheDomain.ConnectDevice();
        connectDevice.setDeviceType(connectRequest.getDeviceTypeEnum().getCode())
                .setServerLocation(serverProperties.buildServerLocation()).setConnectionTime(LocalDateTime.now());
        connectDomain.setUserId(userId).setConnectDevice(connectDevice);
        this.userConnectedDomainCacheService.saveUserConnectDomain(connectDomain);

        // 设置在线用户状态表
        this.userOnlineOfflineService.online(userId);

        return true;
    }


    /**
     * 断开连接
     *
     * @param channel
     *
     * @return
     */
    public boolean disconnect(Channel channel) {

        Long userId = (Long) channel.attr(AttributeKey.valueOf(ChannelContextAttributeConstants.userIdAttribute)).get();
        this.userLocalChannelManager.removeChannel(userId, channel);

        // 移除连接信息
        DeviceTypeEnum deviceType = (DeviceTypeEnum) channel.attr(AttributeKey.valueOf(ChannelContextAttributeConstants.userDeviceTypeAttribute)).get();
        boolean offline = this.userConnectedDomainCacheService.removeUserConnectAndGetStatus(userId, deviceType.getCode());

        if (BooleanUtils.isTrue(offline)) {
            // 设备全部离线，移除用户在线表
            this.userOnlineOfflineService.offline(userId);
        }

        return true;
    }

}
