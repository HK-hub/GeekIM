package com.geek.im.authorization.bootstrap.handler;

import com.alibaba.fastjson2.JSON;
import com.geek.im.authorization.domain.constant.AuthConstants;
import com.geek.im.common.response.ResponseResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author : HK意境
 * @ClassName : LoginSuccessHandler
 * @date : 2024/4/17 19:02
 * @description : 登录成功处理类
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {


    /**
     * 认证成功
     * 为了支持前后端分离，这里统一相应类不能返回HTML页面，需要相应数据了
     *
     * @param request
     * @param response
     * @param authentication
     *
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // 为了支持前后端分离，这里统一相应类不能返回HTML页面，需要相应数据了
        // 获取请求头
        String header = request.getHeader(AuthConstants.Frontend_Backend_Separation_Header);

        // 设置响应头
        if (StringUtils.isNotEmpty(header)) {
            response.setHeader(AuthConstants.Frontend_Backend_Separation_Header, header);
        }

        ResponseResult<?> success = ResponseResult.SUCCESS().setDataAsMessage("认证成功!");
        // 输入相应流中
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(JSON.toJSONString(success));
        response.getWriter().flush();
    }
}
