package com.geek.im.authorization.interfaces.advice;

import com.geek.im.common.response.ResponseResult;
import com.geek.im.common.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : HK意境
 * @ClassName : GlobalExceptionHandler
 * @date : 2024/4/3 22:02
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 权限不足异常处理
     *
     * @param exception
     *
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseResult<String> handleAuthorizationException(AccessDeniedException exception) {

        log.error("access denied exception: {}", exception.getMessage());
        return ResponseResult.FAIL(ResultCode.FORBIDDEN);
    }


    /**
     * 账号已过期
     *
     * @param e
     *
     * @return
     */
    @ExceptionHandler(AccountExpiredException.class)
    public ResponseResult handleAccountExpiredException(AccountExpiredException e) {
        log.error("account expired exception:", e);
        return ResponseResult.FAIL(e.getMessage());
    }


    /**
     * 用户不存在
     *
     * @param e
     *
     * @return
     */
    @ExceptionHandler(UsernameNotFoundException.class) // 用户名不存在
    public ResponseResult handleUsernameNotFoundException(UsernameNotFoundException e) {

        log.error("user is not exists or not login exception:", e);
        return ResponseResult.FAIL(ResultCode.NO_SUCH_USER);
    }


}
