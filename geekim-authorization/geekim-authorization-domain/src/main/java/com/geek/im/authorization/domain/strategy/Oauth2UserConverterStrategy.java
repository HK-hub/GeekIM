package com.geek.im.authorization.domain.strategy;

import com.geek.im.authorization.domain.entity.Oauth2ThirdAccount;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * @author : HK意境
 * @ClassName : Oauth2UserConverterStrategy
 * @date : 2024/4/19 16:49
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface Oauth2UserConverterStrategy {

    public Oauth2ThirdAccount convert(OAuth2User oAuth2User);

}
