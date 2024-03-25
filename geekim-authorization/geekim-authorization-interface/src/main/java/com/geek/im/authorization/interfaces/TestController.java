package com.geek.im.authorization.interfaces;

import com.geek.im.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : HK意境
 * @ClassName : TestController
 * @date : 2024/3/25 20:09
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@RestController
@RequestMapping()
public class TestController {


    @GetMapping("/test01")
    @PreAuthorize("hasAuthority('SCOPE_message.read')")
    public ResponseResult<String> getUserInfo() {

        return ResponseResult.SUCCESS("{'test01': 'success'}");
    }


    @GetMapping("/test02")
    @PreAuthorize("hasAuthority('SCOPE_message.write')")
    public String test02() {
        return "test02";
    }


}
