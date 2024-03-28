package com.geek.im.authorization.interfaces.web;

import com.geek.im.authorization.domain.value.CaptchaData;
import com.geek.im.authorization.infrastructure.service.CaptchaService;
import com.geek.im.common.response.ResponseResult;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : HK意境
 * @ClassName : CaptchaController
 * @date : 2024/3/28 18:55
 * @description : 验证码功能
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@RestController
@RequestMapping("/verification")
public class CaptchaController {

    @Resource
    private CaptchaService captchaService;


    /**
     * 获取验证码
     *
     * @param request
     *
     * @return
     *
     * @throws Exception
     * @see CaptchaData
     */
    @GetMapping("/captcha")
    public ResponseResult<CaptchaData> captcha(HttpServletRequest request) {

        CaptchaData captchaData = this.captchaService.captcha(request);
        // 转换为响应类
        return ResponseResult.SUCCESS(captchaData);
    }

}