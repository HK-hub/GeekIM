package com.geek.im.authorization.infrastructure.service;

import com.geek.im.authorization.domain.value.CaptchaData;
import com.geek.im.authorization.domain.value.SmsCaptchaParam;
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
    CaptchaData getImageCaptcha(HttpServletRequest request);


    /**
     * 生成验证码写入响应流中
     *
     * @param request
     * @param response
     *
     * @throws IOException
     */
    void getImageCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException;


    /**
     * 获取短信验证码
     *
     * @param request
     *
     * @return
     */
    CaptchaData getSmsCaptcha(SmsCaptchaParam request);

}
