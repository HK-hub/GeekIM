package com.geek.im.authorization.domain.constant;

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


    /**
     * 手机号参数名
     */
    public static final String PHONE_PARAMETER_NAME = "phone";

    /**
     * 短信验证码参数名
     */
    public static final String PHONE_CAPTCHA_PARAMETER_NAME = "smsCaptcha";

    /**
     * 登录方式参数名
     */
    public static final String LOGIN_TYPE_PARAMETER_NAME = "loginType";

    /**
     * 短信登录方式
     */
    public static final CharSequence SMS_LOGIN_TYPE = "smsCaptcha";


    /**
     * 登录方式——账号密码登录
     */
    public static final String PASSWORD_LOGIN_TYPE = "passwordLogin";


    /**
     * 权限在Token中的key
     */
    public static final String AUTHORITIES_KEY = "authorities";

    /**
     * 自定义认证方式-短信验证码
     */
    public static final String GRANT_TYPE_SMS_CODE = "urn:ietf:params:oauth:grant-type:sms_code";


    /**
     * 自定义 grant type —— 短信验证码 —— 手机号的key
     */
    public static final String OAUTH_PARAMETER_NAME_PHONE = PHONE_PARAMETER_NAME;

    /**
     * 自定义 grant type —— 短信验证码 —— 短信验证码的key
     */
    public static final String OAUTH_PARAMETER_NAME_SMS_CAPTCHA = PHONE_CAPTCHA_PARAMETER_NAME;

    /**
     * 角色在token中的key
     */
    public static final String ROLES_CLAIM_NAME = "roles";

    /**
     * 头像在token中的key
     */
    public static final String AVATAR_CLAIM_NAME = "avatar";

    /**
     * 验证码过期时间，默认五分钟
     */
    public static final long CAPTCHA_EXPIRE_TIME = 60 * 5;

    /**
     * 验证码参数名称
     */
    public static final String CAPTCHA_KEY_NAME = "captchaKey";


    /**
     * 随机字符串请求头
     */
    public static final String NONCE_HEADER_NAME = "nonceId";

    /**
     * security context key 前缀
     */
    public static final String SECURITY_CONTEXT_KEY = "auth:security:context:";


    /**
     * 默认过期时间
     */
    public static final Integer DEFAULT_TIMEOUT_SECONDS = 60 * 5;


    /**
     * 自定义前端登录页面(适用于前后端分离场景)
     */
    public static final String CUSTOM_LOGIN_URI = "http://127.0.0.1:5173";


    /**
     * JWK source缓存key
     */
    public static final String AUTHORIZATION_JWS_PREFIX_KEY = "authorization:jws:";


    /**
     * 第三方登录类型前缀key
     */
    public static final String THIRD_LOGIN_TYPE_PREFIX = "login_type_third:";


    /**
     * 第三方登录方式：gitee
     */
    public static final String THIRD_LOGIN_TYPE_GITEE = "gitee";


    /**
     * 第三方登录方式：github
     */
    public static final String THIRD_LOGIN_TYPE_GITHUB = "github";

    /**
     * 前后端登录认证分离请求头
     */
    public static String Frontend_Backend_Separation_Header = "X-Frontend-Backend-Separation-Flag";


    /**
     * 构建短信验证码key
     *
     * @param phone
     *
     * @return
     */
    public static String buildSmsCaptchaKey(String phone) {
        return CAPTCHA_SMS_KEY + phone;
    }


    /**
     * 构建Security Context key
     *
     * @param nonceId
     *
     * @return
     */
    public static String buildSecurityContextKey(String nonceId) {
        return SECURITY_CONTEXT_KEY + nonceId;
    }


    /**
     * 构建第三方登录认证方式key
     *
     * @param third
     *
     * @return
     */
    public static String buildThirdLoginTypeKey(String third) {

        return AuthConstants.THIRD_LOGIN_TYPE_PREFIX + third;
    }
}
