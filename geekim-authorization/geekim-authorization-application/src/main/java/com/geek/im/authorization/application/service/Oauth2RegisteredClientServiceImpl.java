package com.geek.im.authorization.application.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geek.im.authorization.domain.entity.Oauth2RegisteredClient;
import com.geek.im.authorization.domain.mapper.Oauth2RegisteredClientMapper;
import com.geek.im.authorization.domain.service.Oauth2RegisteredClientService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class Oauth2RegisteredClientServiceImpl extends ServiceImpl<Oauth2RegisteredClientMapper, Oauth2RegisteredClient>
        implements Oauth2RegisteredClientService {

}




