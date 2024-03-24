package com.geek.im.authorization.bootstrap.authorization;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Objects;

/**
 * @author : HK意境
 * @ClassName : DeviceClientAuthenticationConverter
 * @date : 2024/3/24 13:15
 * @description : 获取请求中参数转换为DeviceClientAuthenticationToken
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
public class DeviceClientAuthenticationConverter implements AuthenticationConverter {

    private RequestMatcher deviceAuthorizationRequestMatcher;

    private RequestMatcher deviceAccessTokenRequestMatcher;


    public DeviceClientAuthenticationConverter(String deviceAuthorizationEndpointUri) {

        // 获取客户端id
        RequestMatcher clientIdParameterMatcher = new RequestMatcher() {
            @Override
            public boolean matches(HttpServletRequest request) {
                String parameter = request.getParameter(OAuth2ParameterNames.CLIENT_ID);
                return parameter != null;
            }
        };

        // 获取客户端认证请求匹配器
        AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(deviceAuthorizationEndpointUri, HttpMethod.POST.name());
        this.deviceAuthorizationRequestMatcher = new AndRequestMatcher(antPathRequestMatcher, clientIdParameterMatcher);

        // 客户端access_token 请求匹配器
        this.deviceAccessTokenRequestMatcher = new RequestMatcher() {
            @Override
            public boolean matches(HttpServletRequest request) {
                // 认证方式检查
                boolean grantTypeCheck = AuthorizationGrantType.DEVICE_CODE.getValue().equals(request.getParameter(OAuth2ParameterNames.GRANT_TYPE));
                boolean deviceCodeAndClientCheck = Objects.nonNull(request.getParameter(OAuth2ParameterNames.DEVICE_CODE)) && Objects.nonNull(request.getParameter(OAuth2ParameterNames.CLIENT_ID));
                // grant_type 为设备码模式，并且设备码和client_id 不能为空
                return grantTypeCheck && deviceCodeAndClientCheck;
            }
        };
    }


    /**
     * 转换为DeviceClientAuthenticationToken
     *
     * @param request
     *
     * @return
     */
    @Nullable
    @Override
    public Authentication convert(HttpServletRequest request) {

        // 是否符合设备码授权请求
        if (BooleanUtils.isFalse(this.deviceAuthorizationRequestMatcher.matches(request)) &&
                BooleanUtils.isFalse(this.deviceAccessTokenRequestMatcher.matches(request))) {
            return null;
        }

        // 获取client_id
        String clientId = request.getParameter(OAuth2ParameterNames.CLIENT_ID);
        String[] clientIdArrays = request.getParameterValues(OAuth2ParameterNames.CLIENT_ID);
        if (StringUtils.isEmpty(clientId) || ArrayUtils.isEmpty(clientIdArrays) || clientIdArrays.length != 1) {
            // 客户端id不存在或不唯一
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }

        return new DeviceClientAuthenticationToken(clientId, ClientAuthenticationMethod.NONE, null, null);
    }
}
