package com.geek.im.authorization.interfaces.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author : HK意境
 * @ClassName : InvalidCaptchaException
 * @date : 2024/3/28 19:52
 * @description : 验证码异常类
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class InvalidCaptchaException extends AuthenticationException {

    /**
     * Constructs an {@code AuthenticationException} with the specified message and no
     * root cause.
     *
     * @param msg the detail message
     */
    public InvalidCaptchaException(String msg) {
        super(msg);
    }

}
