package com.geek.im.authorization.infrastructure.assembly;

import com.geek.im.authorization.domain.aggregate.Oauth2UserInfo;
import com.geek.im.authorization.domain.entity.Oauth2BasicUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author : HK意境
 * @ClassName : OauthBasicUserConverter
 * @date : 2024/4/23 20:01
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Mapper
public interface OauthBasicUserConverter {

    public static OauthBasicUserConverter INSTANCE = Mappers.getMapper(OauthBasicUserConverter.class);

    Oauth2UserInfo toUserInfo(Oauth2BasicUser basicUser);
}
