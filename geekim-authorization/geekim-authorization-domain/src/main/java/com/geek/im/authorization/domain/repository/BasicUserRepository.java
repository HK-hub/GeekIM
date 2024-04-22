package com.geek.im.authorization.domain.repository;

import com.geek.im.authorization.domain.entity.Oauth2BasicUser;
import org.springframework.data.repository.CrudRepository;

/**
 * @author : HK意境
 * @ClassName : BasicUserRepository
 * @date : 2024/4/10 16:51
 * @description : 认证用户repository
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface BasicUserRepository extends CrudRepository<Oauth2BasicUser, Long> {


    /**
     * 根据用户名获取用户
     *
     * @param username
     *
     * @return
     */
    public Oauth2BasicUser findUserByUsername(String username);


    Oauth2BasicUser findUserByAccount(Oauth2BasicUser basicUser);
}
