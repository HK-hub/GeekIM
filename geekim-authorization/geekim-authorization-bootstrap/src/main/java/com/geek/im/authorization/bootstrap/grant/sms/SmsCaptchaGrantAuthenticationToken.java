package com.geek.im.authorization.bootstrap.grant.sms;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author : HK意境
 * @ClassName : SmsCaptchaGrantAuthenticationToken
 * @date : 2024/4/7 15:12
 * @description : 自定义短信验证码登录token
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Getter
@Setter
public class SmsCaptchaGrantAuthenticationToken extends AbstractAuthenticationToken {

    /**
     * 本次登录申请的scope
     */
    private Set<String> scopes;


    /**
     * 客户端认证信息
     */
    private Authentication clientPrinciple;


    /**
     * 当前请求的参数
     */
    private Map<String, Object> additionalParameters;


    /**
     * 认证方式
     */
    private AuthorizationGrantType authorizationGrantType;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 验证码
     */
    private String smsCaptcha;


    public SmsCaptchaGrantAuthenticationToken(Set<String> scopeSet, Authentication clientPrinciple, Map<String, Object> additionalParameters,
                                              AuthorizationGrantType authorizationGrantType) {
        super(Collections.emptyList());
        this.scopes = scopeSet;
        this.clientPrinciple = clientPrinciple;
        this.additionalParameters = additionalParameters;
        this.authorizationGrantType = authorizationGrantType;
    }


    /**
     * The credentials that prove the principal is correct. This is usually a password,
     * but could be anything relevant to the <code>AuthenticationManager</code>. Callers
     * are expected to populate the credentials.
     *
     * @return the credentials that prove the identity of the <code>Principal</code>
     */
    @Override
    public Object getCredentials() {
        if (Objects.isNull(this.clientPrinciple)) {
            return null;
        }
        return this.clientPrinciple.getCredentials();
    }

    /**
     * The identity of the principal being authenticated. In the case of an authentication
     * request with username and password, this would be the username. Callers are
     * expected to populate the principal for an authentication request.
     * <p>
     * The <tt>AuthenticationManager</tt> implementation will often return an
     * <tt>Authentication</tt> containing richer information as the principal for use by
     * the application. Many of the authentication providers will create a
     * {@code UserDetails} object as the principal.
     *
     * @return the <code>Principal</code> being authenticated or the authenticated
     * principal after authentication.
     */
    @Override
    public Object getPrincipal() {
        return this.clientPrinciple;
    }
}
