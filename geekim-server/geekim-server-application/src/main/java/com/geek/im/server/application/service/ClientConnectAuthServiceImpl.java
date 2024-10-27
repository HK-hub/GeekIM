package com.geek.im.server.application.service;

import com.geek.im.server.domain.aggregate.UserConnectAuthInfo;
import com.geek.im.server.domain.aggregate.UserInfo;
import com.geek.im.server.domain.property.IMServerProperties;
import com.geek.im.server.domain.service.ClientConnectAuthService;
import com.geek.im.server.domain.service.UserConnectedDomainCacheService;
import com.geek.im.server.domain.value.ClientConnectRequest;
import geek.im.server.common.constants.ChannelContextAttributeConstants;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author : HK意境
 * @ClassName : ClientConnectAuthServiceImpl
 * @date : 2024/8/3 20:05
 * @description : 处理客户端设备的连接认证
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Service
public class ClientConnectAuthServiceImpl implements ClientConnectAuthService {

    @Resource
    private IMServerProperties imServerProperties;
    @Resource
    private UserConnectedDomainCacheService userConnectedDomainCacheService;


    /**
     * 处理连接认证
     *
     * @param request
     *
     * @return
     *
     * @throws Exception
     */
    @Override
    public UserConnectAuthInfo authConnectClient(ClientConnectRequest request) throws Exception {

        Channel channel = request.getContext().channel();
        // 获取token
        String token = request.getToken();
        // 获取服务器配置
        channel.attr(AttributeKey.valueOf(ChannelContextAttributeConstants.userConnectServerAttribute)).setIfAbsent(imServerProperties.buildServerLocation());


        // TODO 查询token 是否合法，过期，存在


        // 检查是否允许多端登录


        // 是否允许多设备登录

        // 将加解密密钥设置进入channel

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("HK意境");

        UserConnectAuthInfo connectAuthInfo = new UserConnectAuthInfo();

        connectAuthInfo.setUserInfo(userInfo);

        // 设置一些必要信息到context中
        channel.attr(AttributeKey.valueOf(ChannelContextAttributeConstants.userIdAttribute)).setIfAbsent(111111111111111L);
        channel.attr(AttributeKey.valueOf(ChannelContextAttributeConstants.usernameAttribute)).setIfAbsent("意境");

        return connectAuthInfo;
    }
}
