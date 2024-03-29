package com.geek.im.message.interfaces.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : HK意境
 * @ClassName : SimpleMessageController
 * @date : 2024/3/29 21:27
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@RestController
@RequestMapping("/simple")
public class SimpleMessageController {

    @GetMapping("/read")
    @PreAuthorize("hasAuthority('SCOPE_message.read')")
    public String test01() {
        return "test01";
    }

    @GetMapping("/send")
    @PreAuthorize("hasAuthority('SCOPE_message.write')")
    public String test02() {
        return "send success!";
    }

}
