package com.geek.im.authorization.bootstrap.entrypoint;

import com.geek.im.authorization.domain.constant.AuthConstants;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author : HK意境
 * @ClassName : LoginTargetAuthenticationEntryPoint
 * @date : 2024/4/17 19:30
 * @description : 重定向至登录处理
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
public class LoginTargetAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    /**
     * 传入前端登录页面url
     *
     * @param loginFormUrl URL where the login page can be found. Should either be
     *                     relative to the web-app context path (include a leading {@code /}) or an absolute
     *                     URL.
     */
    public LoginTargetAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        // 获取登录表单的登录地址
        String loginForm = this.determineUrlToUseForThisRequest(request, response, authException);
        // 是否绝对路径来得知是否前后端分离页面登录
        if (BooleanUtils.isFalse(UrlUtils.isAbsoluteUrl(loginForm))) {
            // 不是绝对地址调用父类方法处理
            super.commence(request, response, authException);
            return;
        }

        // 是绝对地址
        StringBuffer requestUrl = request.getRequestURL();
        // 获取Query参数
        String queryString = request.getQueryString();
        if (Objects.nonNull(queryString)) {
            requestUrl.append("?").append(queryString);
        }

        // 绝对路径在重定向前添加target参数
        // 重定向地址添加nonceId参数，该值为sessionId
        String targetParameter = URLEncoder.encode(requestUrl.toString(), StandardCharsets.UTF_8);
        UriComponents targetUrlComponent = UriComponentsBuilder.fromHttpUrl(loginForm)
                .queryParam("target", targetParameter)
                .queryParam(AuthConstants.NONCE_HEADER_NAME, request.getSession(false).getId())
                .build();
        // 转换为目标url
        String targetUrl = targetUrlComponent.toString();

        // 重定向至前后端分离的登录页面
        log.info("重定向至前后端分离的登录页面：loginForm={},requestUrl={},targetUrl={}", loginForm, requestUrl, targetUrl);

        this.redirectStrategy.sendRedirect(request, response, targetUrl);
    }
}
