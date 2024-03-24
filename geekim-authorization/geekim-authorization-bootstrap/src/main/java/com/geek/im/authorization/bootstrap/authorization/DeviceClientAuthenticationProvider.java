package com.geek.im.authorization.bootstrap.authorization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.Objects;
import java.util.Set;

/**
 * @author : HK意境
 * @ClassName : DeviceClientAuthenticationProvider
 * @date : 2024/3/24 13:42
 * @description : 设备码认证提供者
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 * @see DeviceClientAuthenticationConverter
 * @see DeviceClientAuthenticationToken
 */
@Slf4j
@RequiredArgsConstructor
public class DeviceClientAuthenticationProvider implements AuthenticationProvider {

    private final RegisteredClientRepository registeredClientRepository;

    /**
     * 异常说明地址
     */
    private static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-3.2.1";


    /**
     * 执行认证生成完整的认证信息
     *
     * @param authentication the authentication request object.
     *
     * @return
     *
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 转换为设备码认证Token，执行时肯定是设备码流程
        DeviceClientAuthenticationToken deviceClientAuthentication = (DeviceClientAuthenticationToken) authentication;

        // 只支持公共客户端
        if (BooleanUtils.isFalse(ClientAuthenticationMethod.NONE.equals(deviceClientAuthentication.getClientAuthenticationMethod()))) {
            // 非公共客户端
            return null;
        }

        // 根据clientId 查询客户端信息
        String clientId = deviceClientAuthentication.getPrincipal().toString();
        RegisteredClient registeredClient = this.registeredClientRepository.findByClientId(clientId);
        if (Objects.isNull(registeredClient)) {
            // 客户端不存在
            throw throwInvalidClientException(OAuth2ParameterNames.CLIENT_ID);
        }

        if (log.isTraceEnabled()) {
            log.trace("Retrieved registered client");
        }

        // 检验客户端认证方式
        Set<ClientAuthenticationMethod> clientAuthenticationMethodSet = registeredClient.getClientAuthenticationMethods();
        if (CollectionUtils.isEmpty(clientAuthenticationMethodSet) ||
                BooleanUtils.isFalse(clientAuthenticationMethodSet.contains(deviceClientAuthentication.getClientAuthenticationMethod()))) {
            // 客户端认证方式不匹配
            throw throwInvalidClientException("authentication_method");
        }

        if (log.isTraceEnabled()) {
            log.trace("Validated device client authentication parameters");
        }

        if (log.isTraceEnabled()) {
            log.trace("Authenticated device client");
        }

        // 想要返回完整的Authentication
        return new DeviceClientAuthenticationToken(registeredClient, deviceClientAuthentication.getClientAuthenticationMethod(), null);
    }


    /**
     * 是否支持的认证请求类型
     *
     * @param authentication
     *
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        // 只支持设备码请求
        return DeviceClientAuthenticationToken.class.isAssignableFrom(authentication);
    }


    /**
     * 抛出无效客户端异常
     *
     * @param message 异常信息
     */
    private static OAuth2AuthenticationException throwInvalidClientException(String message) {
        OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.INVALID_CLIENT, "Device client authentication failed: " + message, ERROR_URI);
        return new OAuth2AuthenticationException(error);
    }

}
