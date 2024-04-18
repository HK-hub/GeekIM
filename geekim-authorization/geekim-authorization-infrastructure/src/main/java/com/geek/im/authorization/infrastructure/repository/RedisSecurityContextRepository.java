package com.geek.im.authorization.infrastructure.repository;

import com.geek.im.authorization.domain.constant.AuthConstants;
import com.geek.im.authorization.domain.model.SupplierDeferredSecurityContext;
import com.geek.im.authorization.infrastructure.cache.RedisUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.DeferredSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author : HK意境
 * @ClassName : RedisSecurityContextRepository
 * @date : 2024/4/17 20:01
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
public class RedisSecurityContextRepository implements SecurityContextRepository {

    @Resource
    private RedisUtil redisUtil;

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {

        // 方法已过时,使用loadDeferredContext 方法
        throw new UnsupportedOperationException("Method deprecated");
    }


    /**
     * 加载认证信息
     *
     * @param request
     *
     * @return
     */
    @Override
    public DeferredSecurityContext loadDeferredContext(HttpServletRequest request) {

        Supplier<SecurityContext> supplier = () -> this.readSecurityContextFromRedis(request);
        return new SupplierDeferredSecurityContext(supplier, this.securityContextHolderStrategy);
    }


    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {

        // 获取nonceId
        String nonceId = getNonceId(request);
        if (StringUtils.isEmpty(nonceId)) {
            return;
        }

        // 如果当前的context是空的则移除
        SecurityContext emptyContext = this.securityContextHolderStrategy.createEmptyContext();

        String securityContextKey = AuthConstants.buildSecurityContextKey(nonceId);
        if (Objects.equals(emptyContext, context)) {
            this.redisUtil.delete(securityContextKey);
        } else {
            // 保存当前信息
            this.redisUtil.set(securityContextKey, context, AuthConstants.DEFAULT_TIMEOUT_SECONDS);
        }

    }


    /**
     * 是否包含认证信息
     *
     * @param request
     *
     * @return
     */
    @Override
    public boolean containsContext(HttpServletRequest request) {

        String nonceId = this.getNonceId(request);

        if (Objects.isNull(nonceId)) {
            return false;
        }

        String securityContextKey = AuthConstants.buildSecurityContextKey(nonceId);
        // 检验当前请求是否有认证信息
        boolean exists = this.redisUtil.hasKey(securityContextKey);
        return exists;
    }


    /**
     * 从redis中加载认证信息
     *
     * @param request
     *
     * @return
     */
    private SecurityContext readSecurityContextFromRedis(HttpServletRequest request) {

        if (Objects.isNull(request)) {
            return null;
        }

        // 获取nonceId
        String nonceId = this.getNonceId(request);
        if (StringUtils.isEmpty(nonceId)) {
            return null;
        }

        // 根据id从缓存中获取认证信息
        SecurityContext securityContext = (SecurityContext) this.redisUtil.get(AuthConstants.buildSecurityContextKey(nonceId));
        return securityContext;
    }


    /**
     * 获取nonceId
     * 先从请求头中寻找，如果不存在则去请求参数中获取，找不到获取当前sessionId
     *
     * @param request
     *
     * @return 随机字符串。随机字符串(sessionId)，这个字符串本来是前端生成，现在改为后端获取的sessionId
     */
    private String getNonceId(HttpServletRequest request) {

        if (Objects.isNull(request)) {
            return null;
        }

        String nonceId = request.getHeader(AuthConstants.NONCE_HEADER_NAME);
        if (Objects.isNull(nonceId)) {
            // 请求头为空，获取请求参数
            nonceId = request.getParameter(AuthConstants.NONCE_HEADER_NAME);
            if (Objects.isNull(nonceId)) {
                // 请求参数为空，获取session
                HttpSession session = request.getSession(false);
                if (Objects.nonNull(session)) {
                    nonceId = session.getId();
                }
            }
        }

        return nonceId;
    }
}
