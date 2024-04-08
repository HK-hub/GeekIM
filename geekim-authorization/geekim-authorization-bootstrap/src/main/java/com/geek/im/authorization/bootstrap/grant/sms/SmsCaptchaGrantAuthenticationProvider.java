package com.geek.im.authorization.bootstrap.grant.sms;

import com.geek.im.authorization.domain.constant.AuthConstants;
import com.geek.im.authorization.infrastructure.utils.SecurityUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : HK意境
 * @ClassName : SmsCaptchaGrantAuthenticationProvider
 * @date : 2024/4/7 21:12
 * @description : 短信验证码登录认证提供者
 * 校验请求参数，并且创建AccessToken返回
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Getter
@Setter
@Accessors(chain = true)
public class SmsCaptchaGrantAuthenticationProvider implements AuthenticationProvider {

    private OAuth2TokenGenerator<?> oAuth2TokenGenerator;

    /**
     * 关联认证管理器
     */
    private AuthenticationManager authenticationManager;

    /**
     * 认证服务：JdbcOAuth2AuthorizationService
     */
    private OAuth2AuthorizationService oAuth2AuthorizationService;

    private static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";

    private static final OAuth2TokenType ID_TOKEN_TOKEN_TYPE = new OAuth2TokenType(OidcParameterNames.ID_TOKEN);


    /**
     * Performs authentication with the same contract as
     * {@link AuthenticationManager#authenticate(Authentication)}
     * .
     *
     * @param authentication the authentication request object.
     *
     * @return a fully authenticated object including credentials. May return
     * <code>null</code> if the <code>AuthenticationProvider</code> is unable to support
     * authentication of the passed <code>Authentication</code> object. In such a case,
     * the next <code>AuthenticationProvider</code> that supports the presented
     * <code>Authentication</code> class will be tried.
     *
     * @throws AuthenticationException if authentication fails.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 进行参数校验，认证，产生完整的AccessToken

        SmsCaptchaGrantAuthenticationToken authenticationToken = (SmsCaptchaGrantAuthenticationToken) authentication;

        // 校验客户端已经进行了认证
        OAuth2ClientAuthenticationToken clientPrinciple = SecurityUtil.getAuthenticatedClient(authenticationToken);

        // 校验客户端是否支持该授权类型
        RegisteredClient registeredClient = clientPrinciple.getRegisteredClient();
        if (Objects.isNull(registeredClient)) {
            // 客户端不存在
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT);
        }

        // 校验授权类型
        if (Objects.isNull(registeredClient.getAuthorizationGrantTypes()) ||
                BooleanUtils.isFalse(registeredClient.getAuthorizationGrantTypes().contains(authenticationToken.getAuthorizationGrantType()))) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT);
        }

        // 验证scope
        Set<String> authorizedScopes = this.getAuthorizedScopes(registeredClient, authenticationToken.getScopes());

        // 进行认证
        Authentication authenticate = this.getAuthenticatedUser(authenticationToken);

        // 构建认证上下文
        DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
                .registeredClient(registeredClient)
                .principal(authenticate)
                .authorizationGrantType(authenticationToken.getAuthorizationGrantType())
                .authorizationGrant(authenticationToken)
                .authorizedScopes(authorizedScopes)
                .authorizationServerContext(AuthorizationServerContextHolder.getContext());

        // 初始化 OAuth2Authorization
        OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient)
                // 存入授权scope
                .authorizedScopes(authorizedScopes)
                // 当前授权用户名称
                .principalName(authenticate.getName())
                // 设置当前用户认证信息。加入当前用户认证信息，防止刷新token时因获取不到认证信息而抛出空指针异常
                .attribute(Principal.class.getName(), authenticate)
                // 认证方式
                .authorizationGrantType(authenticationToken.getAuthorizationGrantType());

        // 创建 access_token 上下文环境
        DefaultOAuth2TokenContext tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.ACCESS_TOKEN).build();
        OAuth2Token generatedAccessToken = this.oAuth2TokenGenerator.generate(tokenContext);

        // 创建 Access Token 失败
        if (Objects.isNull(generatedAccessToken)) {

            OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR, "Then token generator failed to generate the access token.", ERROR_URI);
            throw new OAuth2AuthenticationException(error);
        }

        if (log.isTraceEnabled()) {
            log.trace("Generated access token:{}", generatedAccessToken);
        }

        // 创建Access Token
        OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, generatedAccessToken.getTokenValue(),
                generatedAccessToken.getIssuedAt(), generatedAccessToken.getExpiresAt());

        // 设置Access Token
        if (generatedAccessToken instanceof ClaimAccessor) {
            authorizationBuilder.token(accessToken, (metadata) ->
                    metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME, ((ClaimAccessor) generatedAccessToken).getClaims()));
        } else {
            authorizationBuilder.accessToken(accessToken);
        }


        // 开始构建 Refresh Token
        OAuth2RefreshToken refreshToken = null;

        // 判断是否为公共客户端，客户端是否支持刷新token
        boolean check = registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.REFRESH_TOKEN) && BooleanUtils.isFalse(clientPrinciple.getClientAuthenticationMethod().equals(ClientAuthenticationMethod.NONE));
        if (check) {
            // 支持Refresh Token 并且不是公共客户端

            // Refresh Token 上下文
            tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.REFRESH_TOKEN).build();
            OAuth2Token generatedRefreshToken = this.oAuth2TokenGenerator.generate(tokenContext);

            // 如果不属于刷新token
            if (BooleanUtils.isFalse(generatedRefreshToken instanceof OAuth2RefreshToken)) {
                // 生成Refresh Token 失败
                OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR, "The token generator failed to generate the refresh token.", ERROR_URI);
                throw new OAuth2AuthenticationException(error);
            }

            if (log.isTraceEnabled()) {
                log.trace("Generated refresh token:{}", generatedRefreshToken);
            }

            refreshToken = (OAuth2RefreshToken) generatedRefreshToken;
            authorizationBuilder.refreshToken(refreshToken);
        }

        // 开始构建ID Token
        OidcIdToken oidcIdToken = null;
        // 是否申请OPENID
        if (authorizedScopes.contains(OidcScopes.OPENID)) {
            tokenContext = tokenContextBuilder
                    .tokenType(ID_TOKEN_TOKEN_TYPE)
                    // ID Token定制器可能需要访问Access Token 或Refresh Token
                    .authorization(authorizationBuilder.build())
                    .build();
            // 生成idToken
            OAuth2Token generateIdToken = this.oAuth2TokenGenerator.generate(tokenContext);
            if (BooleanUtils.isFalse(generateIdToken instanceof Jwt)) {
                // 生成的不属于Jwt
                OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR,
                        "The token generator failed to generate the Id Token", ERROR_URI);
                throw new OAuth2AuthenticationException(error);
            }

            if (log.isTraceEnabled()) {
                log.trace("Generated Id Token:{}", generateIdToken);
            }

            oidcIdToken = new OidcIdToken(generateIdToken.getTokenValue(), generateIdToken.getIssuedAt(), generateIdToken.getExpiresAt(), ((Jwt) generateIdToken).getClaims());
            OidcIdToken finalOidcIdToken = oidcIdToken;
            authorizationBuilder.token(oidcIdToken, metadata ->
                    metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME, finalOidcIdToken.getClaims()));
        }

        // 构建出完整的Authorization
        OAuth2Authorization authorization = authorizationBuilder.build();

        // 保存认证授权信息
        this.oAuth2AuthorizationService.save(authorization);

        Map<String, Object> additionalParameters = new HashMap<>();
        if (Objects.nonNull(oidcIdToken)) {
            // 放入Id Token
            additionalParameters.put(OidcParameterNames.ID_TOKEN, oidcIdToken.getClaims());
        }

        return new OAuth2AccessTokenAuthenticationToken(registeredClient, clientPrinciple, accessToken, refreshToken, additionalParameters);
    }


    /**
     * 根据AuthenticationToken 获取认证用户信息
     *
     * @param authenticationToken converter构建的认证信息，这里是包含手机号与验证码的
     *
     * @return
     */
    private Authentication getAuthenticatedUser(SmsCaptchaGrantAuthenticationToken authenticationToken) {

        // 获取手机号验证码
        Map<String, Object> additionalParameters = authenticationToken.getAdditionalParameters();
        String phone = (String) additionalParameters.get(AuthConstants.OAUTH_PARAMETER_NAME_PHONE);
        String smsCaptcha = (String) additionalParameters.get(AuthConstants.OAUTH_PARAMETER_NAME_SMS_CAPTCHA);

        // 构建UsernamePasswordAuthenticationToken 通过AbstractUserDetailsAuthenticationProvider 及其子类对手机号验证码进行校验
        // 这里就是我说的短信验证与密码模式区别不大，如果是短信验证模式则在SmsCaptchaLoginAuthenticationProvider中加一个校验，
        // 使框架支持手机号、验证码校验，反之不加就是账号密码登录
        UsernamePasswordAuthenticationToken unauthenticated = UsernamePasswordAuthenticationToken.unauthenticated(phone, smsCaptcha);

        Authentication authentication = null;
        try {
            authentication = this.authenticationManager.authenticate(unauthenticated);
        } catch (Exception e) {
            SecurityUtil.throwError(OAuth2ErrorCodes.INVALID_REQUEST, "认证失败：手机号或验证码错误!", ERROR_URI);

        }

        return authentication;
    }


    /**
     * 获取授权范围
     *
     * @param registeredClient
     * @param requestScopes    申请的权限
     *
     * @return
     */
    private Set<String> getAuthorizedScopes(RegisteredClient registeredClient, Set<String> requestScopes) {

        // 获取客户端注册授权范围
        Set<String> authorizedScopes = registeredClient.getScopes();

        if (Objects.nonNull(requestScopes)) {
            // 获取没有被授权的范围
            Set<String> finalAuthorizedScopes = authorizedScopes;
            Set<String> unauthorizedScopes = requestScopes.stream()
                    .filter(scope -> !finalAuthorizedScopes.contains(scope))
                    .collect(Collectors.toSet());
            if (CollectionUtils.isNotEmpty(unauthorizedScopes)) {
                // 申请了授权范围之外的权限
                SecurityUtil.throwError(OAuth2ErrorCodes.INVALID_REQUEST,
                        "OAuth 2.0 Parameter: " + OAuth2ParameterNames.SCOPE, ERROR_URI);
            }

            // 返回请求授权范围
            authorizedScopes = new LinkedHashSet<>(requestScopes);
        }

        if (log.isTraceEnabled()) {
            log.trace("Validated request parameters and Granted scopes: " + authorizedScopes);
        }

        return authorizedScopes;
    }


    /**
     * Returns <code>true</code> if this <Code>AuthenticationProvider</code> supports the
     * indicated <Code>Authentication</code> object.
     * <p>
     * Returning <code>true</code> does not guarantee an
     * <code>AuthenticationProvider</code> will be able to authenticate the presented
     * instance of the <code>Authentication</code> class. It simply indicates it can
     * support closer evaluation of it. An <code>AuthenticationProvider</code> can still
     * return <code>null</code> from the {@link #authenticate(Authentication)} method to
     * indicate another <code>AuthenticationProvider</code> should be tried.
     * </p>
     * <p>
     * Selection of an <code>AuthenticationProvider</code> capable of performing
     * authentication is conducted at runtime the <code>ProviderManager</code>.
     * </p>
     *
     * @param authentication
     *
     * @return <code>true</code> if the implementation can more closely evaluate the
     * <code>Authentication</code> class presented
     */
    @Override
    public boolean supports(Class<?> authentication) {

        // 是否短信验证码登录认证token
        return SmsCaptchaGrantAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
