package com.geek.im.authorization.bootstrap.authorization;

import com.geek.im.authorization.domain.constant.AuthConstants;
import com.geek.im.authorization.infrastructure.cache.RedisUtil;
import com.geek.im.authorization.interfaces.exception.InvalidCaptchaException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * @author : HK意境
 * @ClassName : SmsCaptchaLoginAuthenticationProvider
 * @date : 2024/3/31 18:58
 * @description : 短信验证码登录逻辑
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
public class SmsCaptchaLoginAuthenticationProvider extends CaptchaAuthenticationProvider {


    /**
     * @param userDetailsService 用户服务，给框架提供用户信息
     * @param passwordEncoder    密码解析器，用于加密和校验密码
     */
    public SmsCaptchaLoginAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, RedisUtil redisUtil) {
        super(userDetailsService, passwordEncoder);
        this.redisUtil = redisUtil;

    }


    /**
     * 附加的认证检查。此步骤在authenticate() 方法内部最后步骤执行
     *
     * @param userDetails    as retrieved from the
     *                       {@link #retrieveUser(String, UsernamePasswordAuthenticationToken)} or
     *                       <code>UserCache</code>
     * @param authentication the current request that needs to be authenticated
     *
     * @throws AuthenticationException
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        log.info("Authenticate sms captcha...");

        // 获取凭据
        if (authentication.getCredentials() == null) {
            log.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException("The sms captcha can not be empty.");
        }

        // 获取当前Request
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(requestAttributes)) {
            throw new InvalidCaptchaException("Failed to get current request.");
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        // 获取当前登录方式
        String loginType = request.getParameter(AuthConstants.LOGIN_TYPE_PARAMETER_NAME);
        // 获取认证方式
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);


        // 短信登录和自定义短信认证grant type
        // 如果登录方式为短信登录
        if (StringUtils.equalsIgnoreCase(loginType, AuthConstants.SMS_LOGIN_TYPE)
                && Objects.equals(AuthConstants.GRANT_TYPE_SMS_CODE, grantType)) {

            /*// 获取存入session的验证码(UsernamePasswordAuthenticationToken的principal中现在存入的是手机号)
            String smsCaptcha = (String) request.getSession(Boolean.FALSE).getAttribute((String) authentication.getPrincipal());
            // 校验输入的验证码是否正确(UsernamePasswordAuthenticationToken的credentials中现在存入的是输入的验证码)
            if (!Objects.equals(smsCaptcha, authentication.getCredentials())) {
                throw new BadCredentialsException("The sms captcha is incorrect.");
            }*/

            // 获取请求参数中的手机号和验证码
            String phone = request.getParameter(AuthConstants.PHONE_PARAMETER_NAME);
            // 手机号
            if (Objects.isNull(phone)) {
                // 手机号为空，存储在username 中
                phone = (String) authentication.getPrincipal();
            }

            // 验证码
            String smsCaptcha = request.getParameter(AuthConstants.PHONE_CAPTCHA_PARAMETER_NAME);
            if (Objects.isNull(smsCaptcha)) {
                smsCaptcha = (String) authentication.getCredentials();
            }

            // 校验是否合法
            if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(smsCaptcha)) {
                throw new BadCredentialsException("手机号和验证码不能为空!");
            }

            // 获取存储的验证码
            String cachedSmsCaptcha = (String) this.redisUtil.get(AuthConstants.buildSmsCaptchaKey(phone));
            if (!Objects.equals(smsCaptcha, cachedSmsCaptcha)) {
                throw new BadCredentialsException("The sms captcha is incorrect.");
            }

            // 这里也可以扩展其他的登录方式，例如邮箱登录

        } else {
            log.info("Not special login type.");
            // 其他方式调用父类默认实现的密码登录方式
            super.additionalAuthenticationChecks(userDetails, authentication);
        }

        log.info("Authenticated sms captcha.");
    }
}
