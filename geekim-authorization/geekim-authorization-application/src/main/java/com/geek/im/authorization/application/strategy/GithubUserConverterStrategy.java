package com.geek.im.authorization.application.strategy;

import com.geek.im.authorization.domain.constant.AuthConstants;
import com.geek.im.authorization.domain.entity.Oauth2ThirdAccount;
import com.geek.im.authorization.domain.strategy.Oauth2UserConverterStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : GithubUserConverterStrategy
 * @date : 2024/4/19 16:51
 * @description : 第三方登录Github: 转换认证身份信息为第三方账户信息
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Component(AuthConstants.THIRD_LOGIN_TYPE_PREFIX + AuthConstants.THIRD_LOGIN_TYPE_GITHUB)
@RequiredArgsConstructor
public class GithubUserConverterStrategy implements Oauth2UserConverterStrategy {

    private final GiteeUserConverterStrategy giteeUserConverterStrategy;

    protected static final String LOGIN_TYPE = "github";


    @Override
    public Oauth2ThirdAccount convert(OAuth2User oAuth2User) {

        // github 与 gitee 目前所取字段一致，直接调用gitee解析
        Oauth2ThirdAccount thirdAccount = this.giteeUserConverterStrategy.convert(oAuth2User);

        // 提起location
        Map<String, Object> attributes = oAuth2User.getAttributes();
        thirdAccount.setLocation((String) attributes.get("location"));

        // 设置登录类型
        thirdAccount.setType(LOGIN_TYPE);
        return thirdAccount;
    }


}
