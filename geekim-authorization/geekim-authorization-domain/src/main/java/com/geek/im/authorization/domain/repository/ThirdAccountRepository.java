package com.geek.im.authorization.domain.repository;

import com.geek.im.authorization.domain.entity.Oauth2ThirdAccount;
import org.springframework.data.repository.CrudRepository;

/**
 * @author : HK意境
 * @ClassName : ThirdAccountRepository
 * @date : 2024/4/22 19:45
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface ThirdAccountRepository extends CrudRepository<Oauth2ThirdAccount, Long> {

    /**
     * 查询唯一第三方认证账户
     *
     * @param type     登录方式
     * @param uniqueId 三方登录唯一id
     *
     * @return
     */
    Oauth2ThirdAccount getUniqueThirdAccount(String type, String uniqueId);

    Oauth2ThirdAccount findByUserId(Long userId);

    /**
     * 根据三方信息供应商去查询三方账户
     *
     * @param account  第三方账户
     * @param provider 第三方供应商
     *
     * @return
     */
    Oauth2ThirdAccount findThirdAccountByProvider(String account, String provider);
}
