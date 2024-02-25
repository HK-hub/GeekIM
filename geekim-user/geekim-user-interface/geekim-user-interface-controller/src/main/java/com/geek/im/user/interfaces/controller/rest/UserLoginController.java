package com.geek.im.user.interfaces.controller.rest;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.geek.im.user.domain.dto.UserLoginDTO;
import com.geek.im.user.domain.service.UserLoginService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : HK意境
 * @ClassName : UserLoginController
 * @date : 2024/2/25 12:12
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class UserLoginController {

    @Resource
    private UserLoginService userLoginService;

    @SentinelResource(value = "hello", fallback = "sentinelFallback")
    @GetMapping("/hello/sentinel")
    public String sentinel(){
        return "hello sentinel";
    }

    @SentinelResource("user:login:controller")
    @GetMapping("/sample")
    public ResponseEntity<String> login() {
        log.info("login");
        UserLoginDTO login = this.userLoginService.login(null);
        return ResponseEntity.ok("登录成功!");
    }

    public String sentinelFallback() {
        return "hello fallback";
    }

}
