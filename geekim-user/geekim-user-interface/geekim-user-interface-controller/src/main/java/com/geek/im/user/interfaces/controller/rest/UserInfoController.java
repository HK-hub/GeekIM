package com.geek.im.user.interfaces.controller.rest;

import com.geek.im.common.response.ResponseResult;
import com.geek.im.user.domain.dto.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : HK意境
 * @ClassName : UserInfoController
 * @date : 2024/3/29 20:50
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@RestController
@RequestMapping("/info")
public class UserInfoController {


    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/detail")
    public ResponseResult<UserInfoDTO> getUserInfo() {


        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUsername("HK意境");
        userInfoDTO.setEmail("3161880795@qq.com");

        return ResponseResult.SUCCESS(userInfoDTO);
    }

}
