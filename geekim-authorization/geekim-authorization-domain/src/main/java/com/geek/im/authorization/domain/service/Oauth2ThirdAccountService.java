package com.geek.im.authorization.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geek.im.authorization.domain.entity.Oauth2ThirdAccount;

/**
 *
 */
public interface Oauth2ThirdAccountService extends IService<Oauth2ThirdAccount> {

    boolean checkAndSaveUser(Oauth2ThirdAccount thirdAccount);

    Oauth2ThirdAccount getThirdAccountByUserId(Long userId);

    Oauth2ThirdAccount getThirdAccountByProvider(String account, String provider);

}
