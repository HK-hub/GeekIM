package com.geek.im.authorization.application.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geek.im.authorization.domain.entity.Oauth2Authorization;
import com.geek.im.authorization.domain.mapper.Oauth2AuthorizationMapper;
import com.geek.im.authorization.domain.service.Oauth2AuthorizationService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class Oauth2AuthorizationServiceImpl extends ServiceImpl<Oauth2AuthorizationMapper, Oauth2Authorization>
        implements Oauth2AuthorizationService {

}




