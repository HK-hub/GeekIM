package com.geek.im.authorization.bootstrap.grant.sms;

import com.geek.im.authorization.domain.constant.AuthConstants;
import com.geek.im.authorization.infrastructure.utils.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.MultiValueMap;

import java.util.*;

/**
 * @author : HK意境
 * @ClassName : SmsCaptchaGrantAuthenticationConverter
 * @date : 2024/4/7 15:21
 * @description : 短信验证码登录Token转换器
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
public class SmsCaptchaGrantAuthenticationConverter implements AuthenticationConverter {

    private static final String ACCESS_TOKEN_REQUEST_ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";


    /**
     * 转换为Authentication
     *
     * @param request
     *
     * @return
     */
    @Override
    public Authentication convert(HttpServletRequest request) {

        // 校验认证方式
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        // 是否支持的认证方式
        if (BooleanUtils.isFalse(this.supportGrantType(grantType))) {
            return null;
        }

        // 这里目前是客户端认证信息
        Authentication clientPrinciple = SecurityContextHolder.getContext().getAuthentication();

        // 获取请求中的请求参数
        MultiValueMap<String, String> parameters = SecurityUtil.getParameters(request);

        // 获取请求授权的scope
        String scope = parameters.getFirst(OAuth2ParameterNames.SCOPE);
        if (StringUtils.isNotEmpty(scope) && parameters.get(OAuth2ParameterNames.SCOPE).size() != 1) {
            // 因为这里的scopes是按照' '进行分割的字符串
            SecurityUtil.throwError(OAuth2ErrorCodes.INVALID_REQUEST, "OAuth2 Parameter: " + OAuth2ParameterNames.SCOPE, ACCESS_TOKEN_REQUEST_ERROR_URI);
        }

        // 获取已经请求的权限
        Set<String> requestedScopes = null;
        if (StringUtils.isNotEmpty(scope)) {
            requestedScopes = new HashSet<>(Arrays.asList(StringUtils.split(scope, " ")));
        }

        // 获取电话号码(通username)
        String phone = parameters.getFirst(AuthConstants.OAUTH_PARAMETER_NAME_PHONE);
        // 电话号码不存在或存在多个
        if (StringUtils.isEmpty(phone) || parameters.get(AuthConstants.OAUTH_PARAMETER_NAME_PHONE).size() != 1) {
            SecurityUtil.throwError(OAuth2ErrorCodes.INVALID_REQUEST, "OAuth2 Parameter: " + AuthConstants.OAUTH_PARAMETER_NAME_PHONE, ACCESS_TOKEN_REQUEST_ERROR_URI);
        }

        // 获取短信验证码(通password)
        String password = parameters.getFirst(AuthConstants.OAUTH_PARAMETER_NAME_SMS_CAPTCHA);
        if (StringUtils.isEmpty(password) || parameters.get(AuthConstants.OAUTH_PARAMETER_NAME_SMS_CAPTCHA).size() != 1) {
            // 验证码不存在或存在多个
            SecurityUtil.throwError(OAuth2ErrorCodes.INVALID_REQUEST, "OAuth2 Parameter: " + AuthConstants.OAUTH_PARAMETER_NAME_SMS_CAPTCHA, ACCESS_TOKEN_REQUEST_ERROR_URI);
        }

        // 提取附加参数
        Map<String, Object> additionalParameters = new HashMap<>();
        parameters.forEach((key, value) -> {
            if (BooleanUtils.isFalse(OAuth2ParameterNames.GRANT_TYPE.equals(key)) &&
                    BooleanUtils.isFalse(OAuth2ParameterNames.CLIENT_ID.equals(key))) {
                // 添加到附加参数中
                additionalParameters.put(key, value.get(0));
            }
        });


        // 构建AbstractAuthenticationToken实例返回
        SmsCaptchaGrantAuthenticationToken authenticationToken = new SmsCaptchaGrantAuthenticationToken(requestedScopes, clientPrinciple, additionalParameters,
                new AuthorizationGrantType(AuthConstants.GRANT_TYPE_SMS_CODE));
        authenticationToken.setPhone(phone);
        authenticationToken.setSmsCaptcha(password);

        return authenticationToken;
    }


    /**
     * 是否支持认证方式
     *
     * @param grantType
     *
     * @return
     */
    private boolean supportGrantType(String grantType) {
        return AuthConstants.GRANT_TYPE_SMS_CODE.equalsIgnoreCase(grantType);
    }


}
