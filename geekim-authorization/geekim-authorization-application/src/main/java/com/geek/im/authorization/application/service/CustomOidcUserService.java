package com.geek.im.authorization.application.service;

import com.geek.im.authorization.application.context.Oauth2UserConverterContext;
import com.geek.im.authorization.domain.constant.AuthConstants;
import com.geek.im.authorization.domain.entity.Oauth2ThirdAccount;
import com.geek.im.authorization.domain.service.Oauth2ThirdAccountService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : CustomOidcUserService
 * @date : 2024/4/22 18:43
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Service
@RequiredArgsConstructor
public class CustomOidcUserService extends OidcUserService {

    private final Oauth2ThirdAccountService oauth2ThirdAccountService;

    private final Oauth2UserConverterContext oauth2UserConverterContext;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {

        // 获取用户第三方信息
        OidcUser oidcUser = super.loadUser(userRequest);
        // 转为项目中的三方用户信息
        Oauth2ThirdAccount thirdAccount = this.oauth2UserConverterContext.converter(userRequest, oidcUser);
        // 检查用户信息
        this.oauth2ThirdAccountService.checkAndSaveUser(thirdAccount);
        OidcIdToken idToken = oidcUser.getIdToken();

        // 将loginType设置至attributes中
        Map<String, Object> attributes = new LinkedHashMap<String, Object>();
        attributes.put(AuthConstants.LOGIN_TYPE_PARAMETER_NAME, userRequest.getClientRegistration().getRegistrationId());

        // 重新生成一个IdToken
        OidcIdToken oidcIdToken = new OidcIdToken(idToken.getTokenValue(), idToken.getIssuedAt(), idToken.getExpiresAt(), attributes);

        // 用户名字段属性名称
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        // 重新生成oidcUser
        if (StringUtils.isNotEmpty(userNameAttributeName)) {
            return new DefaultOidcUser(oidcUser.getAuthorities(), oidcIdToken, oidcUser.getUserInfo(), userNameAttributeName);
        }

        // 用户名属性字段名称为空
        return new DefaultOidcUser(oidcUser.getAuthorities(), oidcIdToken, oidcUser.getUserInfo());
    }
}
