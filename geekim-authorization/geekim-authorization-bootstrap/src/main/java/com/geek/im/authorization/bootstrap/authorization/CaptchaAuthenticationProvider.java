package com.geek.im.authorization.bootstrap.authorization;

import com.geek.im.authorization.domain.constant.AuthConstants;
import com.geek.im.authorization.infrastructure.cache.RedisUtil;
import com.geek.im.authorization.interfaces.exception.InvalidCaptchaException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * @author : HK意境
 * @ClassName : CaptchaAuthenticationProvider
 * @date : 2024/3/28 20:04
 * @description : 编写图形验证码校验认证.在authenticate方法中添加校验验证码的逻辑，最后调用父类的authenticate方法
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
// @Component
public class CaptchaAuthenticationProvider extends DaoAuthenticationProvider {

    @Getter
    @Setter
    protected RedisUtil redisUtil;


    /**
     * @param userDetailsService 用户服务，给框架提供用户信息
     * @param passwordEncoder    密码解析器，用于加密和校验密码
     */
    public CaptchaAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        super.setUserDetailsService(userDetailsService);
        super.setPasswordEncoder(passwordEncoder);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        log.info("authenticate captcha starting...");

        // 获取当前request
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new InvalidCaptchaException("Failed to get current request");
        }

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        // 获取当前登录方式
        String loginType = request.getParameter(AuthConstants.LOGIN_TYPE_PARAMETER_NAME);
        if (!Objects.equals(loginType, AuthConstants.PASSWORD_LOGIN_TYPE)) {
            // 只要不是账号密码登录则不需要图形验证码校验
            log.info("It isn't necessary image captcha authenticate.");
            return super.authenticate(authentication);
        }


        // 获取参数中的验证码
        String code = request.getParameter("code");
        if (StringUtils.isEmpty(code)) {
            // 图形验证码不存在
            throw new InvalidCaptchaException("The captcha can not be empty");
        }

        log.info("request session get captcha code:{}", code);

        // 获取session中的验证码key
        Object captchaAttribute = request.getSession(Boolean.TRUE).getAttribute("captchaKey");
        if (captchaAttribute instanceof String captchaKey) {
            // 获取缓存中报错的code
            String cachedCode = (String) this.redisUtil.get(captchaKey);

            if (!StringUtils.equalsIgnoreCase(code, cachedCode)) {
                // 图形验证码不匹配
                throw new InvalidCaptchaException("The captcha is incorrect");
            }
            // 删除验证码
            this.redisUtil.delete(captchaKey);
        } else {
            throw new InvalidCaptchaException("The captcha is abnormal. Obtain it again.");
        }

        log.info("Captcha authenticated.");
        return super.authenticate(authentication);
    }
}
