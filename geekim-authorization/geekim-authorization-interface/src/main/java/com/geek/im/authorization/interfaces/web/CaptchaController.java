package com.geek.im.authorization.interfaces.web;

import com.geek.im.authorization.domain.value.CaptchaData;
import com.geek.im.authorization.domain.value.SmsCaptchaParam;
import com.geek.im.authorization.infrastructure.service.CaptchaService;
import com.geek.im.common.response.ResponseResult;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author : HK意境
 * @ClassName : CaptchaController
 * @date : 2024/3/28 18:55
 * @description : 验证码功能接口
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
     * 获取图形验证码
     *
     * @param request
     *
     * @return
     *
     * @throws Exception
     * @see CaptchaData
     */
    @GetMapping("/captcha/image")
    public ResponseResult<CaptchaData> imageCaptcha(HttpServletRequest request) {

        CaptchaData captchaData = this.captchaService.getImageCaptcha(request);
        // 转换为响应类
        return ResponseResult.SUCCESS(captchaData);
    }


    /**
     * 发送短信验证码
     *
     * @param request
     *
     * @return
     */
    @PostMapping("/captcha/sms")
    public ResponseResult<CaptchaData> smsCaptcha(@RequestBody @Validated SmsCaptchaParam request, HttpSession httpSession) {

        // 获取短信验证码
        CaptchaData captchaData = this.captchaService.getSmsCaptcha(request);

        // 放入session中
        httpSession.setAttribute(request.getPhone(), captchaData.getCode());
        // 响应
        return ResponseResult.SUCCESS(captchaData);
    }


}