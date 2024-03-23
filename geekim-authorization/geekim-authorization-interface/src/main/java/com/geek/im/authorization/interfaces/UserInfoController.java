package com.geek.im.authorization.interfaces;

import com.geek.im.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("")
    @PreAuthorize("hasAuthority('SCOPE_message.read')")
    public ResponseResult<String> getUserInfo() {

        return ResponseResult.SUCCESS("{'username': 'hk'}");
    }


}
