package com.geek.im.authorization.bootstrap.authorization;

import org.springframework.lang.Nullable;
import org.springframework.security.core.Transient;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : DeviceClientAuthenticationToken
 * @date : 2024/3/24 13:06
 * @description : 设备客户端模式认证token
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Transient
public class DeviceClientAuthenticationToken extends OAuth2ClientAuthenticationToken {


    public DeviceClientAuthenticationToken(String clientId, ClientAuthenticationMethod clientAuthenticationMethod,
                                           @Nullable Object credentials, @Nullable Map<String, Object> additionalParameters) {
        super(clientId, clientAuthenticationMethod, credentials, additionalParameters);
    }

    public DeviceClientAuthenticationToken(RegisteredClient registeredClient, ClientAuthenticationMethod clientAuthenticationMethod,
                                           @Nullable Object credentials) {
        super(registeredClient, clientAuthenticationMethod, credentials);
    }
}
