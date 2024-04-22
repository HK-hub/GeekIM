package com.geek.im.authorization.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geek.im.authorization.domain.entity.Oauth2BasicUser;
import com.geek.im.authorization.domain.entity.Oauth2ThirdAccount;

/**
 *
 */
public interface Oauth2BasicUserService extends IService<Oauth2BasicUser> {

    Oauth2BasicUser getUserByAccount(Oauth2BasicUser basicUser);

    Oauth2BasicUser saveByThirdAccount(Oauth2ThirdAccount thirdAccount);

    /**
     * 根据第三方登录账户更新基础用户信息
     *
     * @param userId
     * @param thirdAccount
     *
     * @return
     */
    Oauth2BasicUser updateBasicUserByThirdAccount(Long userId, Oauth2ThirdAccount thirdAccount);
}
