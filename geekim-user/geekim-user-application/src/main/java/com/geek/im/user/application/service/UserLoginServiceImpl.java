package com.geek.im.user.application.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.geek.im.user.domain.dto.UserLoginDTO;
import com.geek.im.user.domain.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author : HK意境
 * @ClassName : UserLoginServiceImpl
 * @date : 2024/2/25 16:02
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Service
public class UserLoginServiceImpl implements UserLoginService {


    @Override
    @SentinelResource("user:login")
    public UserLoginDTO login(UserLoginDTO userLoginDTO) {


        return new UserLoginDTO();
    }


}
