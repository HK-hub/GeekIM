package com.geek.im.user.domain.constant;

/**
 * @author : HK意境
 * @ClassName : AuthConstants
 * @date : 2024/3/20 20:22
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class AuthConstants {

    /**
     * 用户确认授权页面链接
     */
    public static final String CUSTOM_CONSENT_PAGE_URI = "/oauth2/consent";

    /**
     * 统一登录uri
     */
    public static final String UNIFIED_LOGIN_URI = "/login";

    /**
     * 客户端密钥
     */
    public static final String CLIENT_SECRET = "123456";

    /**
     * 自定义设备验证uri
     */
    public static final String CUSTOM_DEVICE_VERIFICATION_URI = "/activate";

    /**
     * 权限信息在JWT中的claims的key
     */
    public static final String AUTHORITIES_CLAIM_NAME = "authorities";

    /**
     * 图形验证码key
     */
    public static final String CAPTCHA_IMAGE_KEY = "verification:captcha:image:";

    /**
     * 短信验证码key
     */
    public static final String CAPTCHA_SMS_KEY = "verification:captcha:sms:";
}
