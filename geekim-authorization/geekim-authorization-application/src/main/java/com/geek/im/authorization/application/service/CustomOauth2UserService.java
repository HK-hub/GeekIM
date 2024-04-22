package com.geek.im.authorization.application.service;

import com.geek.im.authorization.application.context.Oauth2UserConverterContext;
import com.geek.im.authorization.domain.constant.AuthConstants;
import com.geek.im.authorization.domain.entity.Oauth2ThirdAccount;
import com.geek.im.authorization.domain.service.Oauth2ThirdAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : CustomOauth2UserService
 * @date : 2024/4/19 16:45
 * @description : 自定义第三方登录获取用户信息服务
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final Oauth2ThirdAccountService oauth2ThirdAccountService;

    private final Oauth2UserConverterContext oauth2UserConverterContext;


    /**
     * 加载用户
     *
     * @param userRequest
     *
     * @return
     *
     * @throws OAuth2AuthenticationException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 转换为项目中的三方用户信息
        Oauth2ThirdAccount thirdAccount = this.oauth2UserConverterContext.converter(userRequest, oAuth2User);
        // 检查用户信息
        this.oauth2ThirdAccountService.checkAndSaveUser(thirdAccount);

        // 将loginType设置至attributes中
        Map<String, Object> attributes = new LinkedHashMap<>(oAuth2User.getAttributes());

        // 将yml配置的RegistrationId 当作登录类型
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        attributes.put(AuthConstants.LOGIN_TYPE_PARAMETER_NAME, registrationId);
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        return new DefaultOAuth2User(oAuth2User.getAuthorities(), attributes, userNameAttributeName);
    }
}
