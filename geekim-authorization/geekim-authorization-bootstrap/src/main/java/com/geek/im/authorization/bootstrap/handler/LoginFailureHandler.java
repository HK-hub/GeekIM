package com.geek.im.authorization.bootstrap.handler;

import com.alibaba.fastjson2.JSON;
import com.geek.im.authorization.domain.constant.AuthConstants;
import com.geek.im.common.response.ResponseResult;
import com.geek.im.common.response.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author : HK意境
 * @ClassName : LoginFailureHandler
 * @date : 2024/4/17 19:22
 * @description : 登录认证失败处理器
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // 为了支持前后端分离，这里统一相应类不能返回HTML页面，需要相应数据了
        // 获取请求头
        String header = request.getHeader(AuthConstants.Frontend_Backend_Separation_Header);

        // 设置响应头
        if (StringUtils.isNotEmpty(header)) {
            response.setHeader(AuthConstants.Frontend_Backend_Separation_Header, header);
        }

        // 登录失败写回具体的异常信息
        ResponseResult<String> fail = ResponseResult.FAIL(ResultCode.UNAUTHORIZED);
        fail.setData(exception.getMessage());

        // 输入相应流中
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(JSON.toJSONString(fail));
        response.getWriter().flush();
    }
}
