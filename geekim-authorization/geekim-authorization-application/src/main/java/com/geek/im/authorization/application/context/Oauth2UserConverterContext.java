package com.geek.im.authorization.application.context;

import com.geek.im.authorization.domain.constant.AuthConstants;
import com.geek.im.authorization.domain.entity.Oauth2ThirdAccount;
import com.geek.im.authorization.domain.strategy.Oauth2UserConverterStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.Objects;

/**
 * @author : HK意境
 * @ClassName : Oauth2UserConverterContext
 * @date : 2024/4/19 16:55
 * @description : 三方oauth2登录获取的用户信息转换处理
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Oauth2UserConverterContext {

    /**
     * 注入所有实例，map的key是实例在ioc中的名字
     * 这里通过构造器注入所有的Oauth2UserConverterStrategy的实例
     * {@link Component }注解指定过bean的名字，可以根据给定bean名称从map中获取对应的实例(如果存在)
     * 想不到吧，Spring还有一招集合注入方法
     */
    private final Map<String, Oauth2UserConverterStrategy> oauth2UserConverterStrategyMap;


    /**
     * 根据登录方式获取转换器实例，使用转换器获取用户信息
     *
     * @param userRequest 获取三方用户信息入参
     * @param oAuth2User  三方登录获取到的认证信息
     *
     * @return
     */
    public Oauth2ThirdAccount converter(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {

        // 获取三方登录配置的registrationId,这里将他当作登录方式
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // 转换用户信息
        Oauth2ThirdAccount thirdAccount = this.getInstance(registrationId).convert(oAuth2User);

        // 获取AccessToken
        OAuth2AccessToken accessToken = userRequest.getAccessToken();
        // 设置token
        thirdAccount.setCredentials(accessToken.getTokenValue());

        // token过期时间
        Instant expiresAt = accessToken.getExpiresAt();
        if (Objects.nonNull(expiresAt)) {
            LocalDateTime tokenExpiresAt = expiresAt.atZone(ZoneId.systemDefault()).toLocalDateTime();
            // token过期时间
            thirdAccount.setCredentialsExpiresAt(tokenExpiresAt);
        }

        return thirdAccount;
    }


    /**
     * 获取转换器实例
     *
     * @param registrationId
     *
     * @return
     */
    private Oauth2UserConverterStrategy getInstance(String registrationId) {

        if (StringUtils.isEmpty(registrationId)) {
            throw new IllegalArgumentException("登录方式不能为空!");
        }

        Oauth2UserConverterStrategy converter = this.oauth2UserConverterStrategyMap.get(AuthConstants.buildThirdLoginTypeKey(registrationId));
        if (Objects.isNull(converter)) {
            throw new UnsupportedOperationException("不支持[" + registrationId + "]登录方式获取用户信息转换器");
        }

        return converter;
    }
}
