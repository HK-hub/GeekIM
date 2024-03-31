package com.geek.im.user.bootstrap.utils;

import com.geek.im.user.infrastructure.utils.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.server.resource.BearerTokenError;
import org.springframework.security.oauth2.server.resource.BearerTokenErrorCodes;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author : HK意境
 * @ClassName : SecurityUtil
 * @date : 2024/3/25 19:26
 * @description : 认证鉴权工具类
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
public class SecurityUtil {


    /**
     * 认证与鉴权失败回调
     *
     * @param request  当前请求
     * @param response 当前响应
     * @param e        具体异常信息
     */
    public static void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Throwable e) {

        // 获取具体异常参数
        Map<String, String> parameters = getErrorParameters(request, response, e);
        // 构建认证鉴权异常响应头
        String wwwAuthenticate = computeWwwAuthenticateHeaderValue(parameters);
        response.addHeader(HttpHeaders.WWW_AUTHENTICATE, wwwAuthenticate);

        // 响应错误信息
        try {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(JsonUtils.objectCovertToJson(parameters));
            response.getWriter().flush();
        } catch (Exception ex) {
            log.error("回写错误信息失败:", ex);
        }
    }


    /**
     * 构建认证鉴权异常响应头
     *
     * @param parameters
     *
     * @return
     */
    private static String computeWwwAuthenticateHeaderValue(Map<String, String> parameters) {

        StringBuilder wwwAuthenticate = new StringBuilder();
        wwwAuthenticate.append("Bearer");

        if (MapUtils.isNotEmpty(parameters)) {
            wwwAuthenticate.append(" ");
            StringJoiner joiner = new StringJoiner(", ");
            parameters.forEach((key, value) -> {
                joiner.add(key).add("=\"").add(value).add("\"");
            });
            wwwAuthenticate.append(joiner.toString());
        }

        return wwwAuthenticate.toString();
    }


    /**
     * 获取异常参数
     *
     * @param request  当前请求
     * @param response 当前响应
     * @param e        具体异常信息
     *
     * @return 异常信息Map
     */
    private static Map<String, String> getErrorParameters(HttpServletRequest request, HttpServletResponse response, Throwable e) {

        Map<String, String> parameters = new LinkedHashMap<>();

        if (request.getUserPrincipal() instanceof AbstractOAuth2TokenAuthenticationToken oAuth2TokenAuthenticationToken) {
            // 权限不足
            parameters.put("error", BearerTokenErrorCodes.INSUFFICIENT_SCOPE);
            parameters.put("error_description", "the request requires higher privileges the provided by the access token");
            parameters.put("error_uri", "https://tools.ietf.org/html/rfc6750#section-3.1");
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }

        // 认证异常
        if (e instanceof OAuth2AuthenticationException authenticationException) {
            // JWT 相关异常。JWT 过期，无效等
            OAuth2Error error = authenticationException.getError();
            parameters.put("error", error.getErrorCode());
            if (StringUtils.isNotEmpty(error.getUri())) {
                parameters.put("error_uri", error.getUri());
            }
            if (StringUtils.isNotEmpty(error.getDescription())) {
                parameters.put("error_description", error.getDescription());
            }

            if (error instanceof BearerTokenError bearerTokenError) {
                // token 异常
                if (StringUtils.isNotEmpty(bearerTokenError.getScope())) {
                    parameters.put("scope", bearerTokenError.getScope());
                }
                response.setStatus(bearerTokenError.getHttpStatus().value());
            }
        }

        // 没有携带JWT访问，没有客户端认证信息
        if (e instanceof InsufficientAuthenticationException insufficientAuthenticationException) {
            parameters.put("error", BearerTokenErrorCodes.INVALID_TOKEN);
            parameters.put("error_description", "No authorized.");
            parameters.put("error_uri", "https://tools.ietf.org/html/rfc6750#section-3.1");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

        parameters.put("message", e.getMessage());
        return parameters;
    }


}
