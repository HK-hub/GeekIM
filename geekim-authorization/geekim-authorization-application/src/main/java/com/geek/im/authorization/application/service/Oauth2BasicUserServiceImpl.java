package com.geek.im.authorization.application.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geek.im.authorization.domain.entity.Oauth2BasicUser;
import com.geek.im.authorization.domain.mapper.Oauth2BasicUserMapper;
import com.geek.im.authorization.domain.service.Oauth2BasicUserService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class Oauth2BasicUserServiceImpl extends ServiceImpl<Oauth2BasicUserMapper, Oauth2BasicUser>
        implements Oauth2BasicUserService {

}




