package com.geek.im.authorization.interfaces.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : UserInfoController
 * @date : 2024/3/23 22:01
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user/info")
public class UserInfoController {


    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/detail")
    public Map<String, Object> getUserInfo(Principal principal) {

        if (!(principal instanceof JwtAuthenticationToken token)) {
            return Collections.emptyMap();
        }

        Map<String, Object> claims = token.getToken().getClaims();
        return claims;
    }


}
