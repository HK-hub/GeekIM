package com.geek.im.authorization.application.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geek.im.authorization.domain.entity.Oauth2AuthorizationConsent;
import com.geek.im.authorization.domain.mapper.Oauth2AuthorizationConsentMapper;
import com.geek.im.authorization.domain.service.Oauth2AuthorizationConsentService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class Oauth2AuthorizationConsentServiceImpl extends ServiceImpl<Oauth2AuthorizationConsentMapper, Oauth2AuthorizationConsent>
        implements Oauth2AuthorizationConsentService {

}




