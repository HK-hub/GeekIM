package com.geek.im.message.interfaces.group;

import com.geek.im.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : HK意境
 * @ClassName : GroupMessageController
 * @date : 2024/3/11 20:30
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@RestController
@RequestMapping("/group")
public class GroupMessageController {

    @PostMapping("/send")
    public ResponseResult sendGroupMessage(String groupId, String msg) {
        log.info("发送群消息");
        return ResponseResult.SUCCESS("发送成功!");
    }

}
