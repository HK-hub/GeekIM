package com.geek.im.authorization.application.service;

import com.apifan.common.random.RandomSource;
import com.geek.im.authorization.domain.constant.AuthConstants;
import com.geek.im.authorization.domain.value.CaptchaData;
import com.geek.im.authorization.domain.value.SmsCaptchaParam;
import com.geek.im.authorization.infrastructure.cache.RedisUtil;
import com.geek.im.authorization.infrastructure.service.CaptchaService;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.util.UUID;

/**
 * @author : HK意境
 * @ClassName : CaptchaServiceImpl
 * @date : 2024/3/28 19:17
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Resource
    private RedisUtil redisUtil;


    /**
     * 生成验证码
     *
     * @param request
     *
     * @return
     */
    @Override
    public CaptchaData getImageCaptcha(HttpServletRequest request) {

        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        // 设置字体

        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);

        // 验证码存入session
        String code = specCaptcha.text().toLowerCase();
        // request.getSession().setAttribute("captcha", code);

        // 输出图片流
        String captchaBase64 = specCaptcha.toBase64();

        CaptchaData captchaData = new CaptchaData();
        String key = AuthConstants.CAPTCHA_IMAGE_KEY + UUID.randomUUID();

        // 设置session中的key
        // request.getSession().setAttribute("captchaKey", key);


        // 设置到redis 中.五分钟有效
        this.redisUtil.set(key, code, AuthConstants.CAPTCHA_EXPIRE_TIME);

        log.info("获取图形验证码:key={},code={}", key, code);
        // 返回响应
        captchaData.setKey(key);
        captchaData.setCode(captchaBase64);
        return captchaData;
    }


    /**
     * 生成图像验证码写入响应流中
     *
     * @param request
     * @param response
     *
     * @return
     *
     * @throws IOException
     */
    @Override
    public void getImageCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        // 设置字体
        specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);

        // 验证码存入session
        request.getSession().setAttribute("captcha", specCaptcha.text().toLowerCase());

        // 输出图片流
        specCaptcha.out(response.getOutputStream());
    }


    /**
     * 获取短信验证码
     *
     * @param request
     *
     * @return
     */
    @Override
    public CaptchaData getSmsCaptcha(SmsCaptchaParam request) {

        // 生成验证码
        int code = RandomSource.numberSource().randomInt(123456, 999999);

        // 存入缓存中
        String key = AuthConstants.buildSmsCaptchaKey(request.getPhone());
        this.redisUtil.set(key, code + "", AuthConstants.CAPTCHA_EXPIRE_TIME);

        log.info("短信验证发送成功:phone={}, code={}", request.getPhone(), code);
        CaptchaData captchaData = new CaptchaData();
        captchaData.setKey(request.getPhone());
        captchaData.setCode(code + "");

        return captchaData;
    }

}
