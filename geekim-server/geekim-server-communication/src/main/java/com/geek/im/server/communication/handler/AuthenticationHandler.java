package com.geek.im.server.communication.handler;

import com.geek.im.server.common.constants.DeviceConnectAuthConstants;
import com.geek.im.server.domain.aggregate.UserConnectAuthInfo;
import com.geek.im.server.domain.property.IMServerProperties;
import com.geek.im.server.domain.service.ClientConnectAuthService;
import com.geek.im.server.domain.value.ClientConnectRequest;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

/**
 * @author : HK意境
 * @ClassName : AuthenticationHandler
 * @date : 2024/4/29 19:02
 * @description : WebSocket 链接Handler
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Getter
@Setter
@Component
@ChannelHandler.Sharable
public class AuthenticationHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Resource
    private ClientConnectAuthService clientConnectAuthService;

    @Resource
    private IMServerProperties imServerProperties;

    public AuthenticationHandler() {
    }


    /**
     * 处理连接握手请求
     *
     * @param context
     * @param fullHttpRequest
     *
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext context, FullHttpRequest fullHttpRequest) throws Exception {

        boolean authSuccessfully = this.doDeviceConnect(context, fullHttpRequest);
        if (authSuccessfully) {
            // 握手认证通过
            // 由于使用的SimpleChannelInboundHandler，所以FullHttpRequest消息的引用计数会自动release，
            // 所以我们需要retain后调用fireChannelRead方法传递给下一个handler
            context.fireChannelRead(fullHttpRequest.retain());
            return;
        }

        // 认证失败，关联连接通道
        context.channel().close();
    }


    /**
     * 协议升级握手认证处理
     *
     * @param context
     * @param fullHttpRequest
     */
    private boolean doDeviceConnect(ChannelHandlerContext context, FullHttpRequest fullHttpRequest) throws Exception {

        String uri = fullHttpRequest.uri();
        HttpHeaders headers = fullHttpRequest.headers();
        log.info("收到握手认证请求：uri={}, headers={}", uri, headers);

        // 去除请求参数等
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(uri).build();
        String path = uriComponents.getPath();
        MultiValueMap<String, String> queryParams = uriComponents.getQueryParams();
        log.info("握手连接请求参数：{}", queryParams);

        // 判断是否entry point
        if (!StringUtils.startsWith(path, this.imServerProperties.getServer().getPath())) {
            // 不是开放端点
            log.info("握手连接请求端点不是目标端点：request entry point={}", path);
            return false;
        }

        // 重写连接端点：uri带查询参数造成无法进行连接
        fullHttpRequest.setUri(this.imServerProperties.getServer().getPath());

        // 判断是否存在Authorization Token
        boolean existsAuthorization = headers.contains(DeviceConnectAuthConstants.authorizationTokenKey) ||
                queryParams.containsKey(DeviceConnectAuthConstants.authorizationTokenKey);
        if (BooleanUtils.isFalse(existsAuthorization)) {
            // token为空
            log.info("握手连接请求认证token信息不存在：queryParams={}, headers={}", queryParams, headers);
            return false;
        }

        // 执行用户登录认证操作
        ClientConnectRequest connectRequest = new ClientConnectRequest();
        connectRequest.setContext(context).setUri(uri).setUriComponents(uriComponents).setHeaders(headers).setQueryParamMap(queryParams);
        UserConnectAuthInfo connectAuthInfo = this.clientConnectAuthService.authConnectClient(connectRequest);

        // 处理认证结果
        if (Objects.isNull(connectAuthInfo)) {
            log.info("握手认证失败：uri={}, headers={}, parameters={}", uri, headers, queryParams);
            return false;
        }

        log.info("握手认证成功：uri={}, headers={}, parameters={}", uri, headers, queryParams);
        return true;
    }


    /**
     * 握手认证异常，关闭通道
     *
     * @param ctx
     * @param cause
     *
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.channel().writeAndFlush(new TextWebSocketFrame(cause.getMessage()));
        ctx.channel().close();
    }
}
