package com.geek.im.authorization.bootstrap.filter;

import com.geek.im.authorization.infrastructure.cache.RedisUtil;
import com.geek.im.authorization.interfaces.exception.InvalidCaptchaException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * @author : HK意境
 * @ClassName : CaptchaAuthenticationFilter
 * @date : 2024/3/28 21:38
 * @description : 验证码过滤器
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
public class CaptchaAuthenticationFilter extends GenericFilterBean {

    private RedisUtil redisUtil;

    private AuthenticationFailureHandler authenticationFailureHandler;

    private RequestMatcher requiresAuthenticationRequestMatcher;


    /**
     * 初始化过滤器
     *
     * @param defaultFilterProcessUrl 拦截的地址
     */
    public CaptchaAuthenticationFilter(String defaultFilterProcessUrl) {

        Assert.hasText(defaultFilterProcessUrl, "defaultFilterProcessUrl can not be null");
        requiresAuthenticationRequestMatcher = new AntPathRequestMatcher(defaultFilterProcessUrl);
        authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler(defaultFilterProcessUrl + "?error");
    }

    public CaptchaAuthenticationFilter(String defaultFilterProcessUrl, RedisUtil redisUtil) {

        Assert.hasText(defaultFilterProcessUrl, "defaultFilterProcessUrl can not be null");
        requiresAuthenticationRequestMatcher = new AntPathRequestMatcher(defaultFilterProcessUrl);
        authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler(defaultFilterProcessUrl + "?error");
        this.redisUtil = redisUtil;
    }

    /**
     * 图形验证码过滤器
     *
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this filter to pass the request and response
     *                 to for further processing
     *
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        this.actualDoFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }


    private void actualDoFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        // 检验是否是post请求并且是需要拦截的地址
        if (!request.getMethod().equals(HttpMethod.POST.toString()) || !requiresAuthenticationRequestMatcher.matches(request)) {
            chain.doFilter(request, response);
            return;
        }

        // 开始校验验证码
        log.info("Authenticate captcha...");

        // 获取请求参数中的code
        String code = request.getParameter("code");
        try {
            // 验证码为空
            if (StringUtils.isEmpty(code)) {
                throw new InvalidCaptchaException("The captcha can not be null");
            }

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
        } catch (AuthenticationException e) {
            this.authenticationFailureHandler.onAuthenticationFailure(request, response, e);
            return;
        }

        log.info("Captcha authenticated.");
        // 验证码校验通过开始执行接下来的逻辑
        chain.doFilter(request, response);
    }


    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        Assert.notNull(authenticationFailureHandler, "failureHandler must be not null!");
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

}
