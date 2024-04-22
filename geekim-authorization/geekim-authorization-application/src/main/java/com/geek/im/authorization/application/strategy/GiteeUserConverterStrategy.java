package com.geek.im.authorization.application.strategy;

import com.geek.im.authorization.domain.constant.AuthConstants;
import com.geek.im.authorization.domain.entity.Oauth2ThirdAccount;
import com.geek.im.authorization.domain.strategy.Oauth2UserConverterStrategy;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : GiteeUserConverterStrategy
 * @date : 2024/4/19 16:50
 * @description : 转换通过码云登录的用户信息
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Component(AuthConstants.THIRD_LOGIN_TYPE_PREFIX + AuthConstants.THIRD_LOGIN_TYPE_GITEE)
public class GiteeUserConverterStrategy implements Oauth2UserConverterStrategy {

    private static final String LOGIN_TYPE = "gitee";


    /**
     * @param oAuth2User
     *
     * @return
     */
    @Override
    public Oauth2ThirdAccount convert(OAuth2User oAuth2User) {

        // 获取三方用户信息
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // 转换为Oauth2ThirdAccount
        Oauth2ThirdAccount thirdAccount = new Oauth2ThirdAccount();
        thirdAccount.setUniqueId(oAuth2User.getName());
        thirdAccount.setThirdUsername(String.valueOf(attributes.get("login")));
        thirdAccount.setType(LOGIN_TYPE);
        thirdAccount.setBlog(String.valueOf(attributes.get("blog")));
        // 设置基础用户信息
        thirdAccount.setName(String.valueOf(attributes.get("name")));
        thirdAccount.setAvatar(String.valueOf(attributes.get("avatar_url")));

        return thirdAccount;
    }
}
