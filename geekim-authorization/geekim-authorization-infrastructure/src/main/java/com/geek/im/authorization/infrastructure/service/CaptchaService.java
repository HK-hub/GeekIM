package com.geek.im.authorization.infrastructure.service;

import com.geek.im.authorization.domain.value.CaptchaData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author : HK意境
 * @ClassName : CaptchaService
 * @date : 2024/3/28 19:17
 * @description : 验证码服务
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface CaptchaService {


    /**
     * 生成验证码
     *
     * @param request
     *
     * @return
     */
    CaptchaData captcha(HttpServletRequest request);


    /**
     * 生成验证码写入响应流中
     *
     * @param request
     * @param response
     *
     * @throws IOException
     */
    void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
