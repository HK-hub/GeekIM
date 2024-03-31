package com.geek.im.user.bootstrap.config;

import com.geek.im.user.bootstrap.utils.SecurityUtil;
import com.geek.im.user.domain.constant.AuthConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author : HK意境
 * @ClassName : ResourceServerConfig
 * @date : 2024/3/30 21:58
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class ResourceServerConfig {


    /**
     * 资源服务器过滤器链配置
     *
     * @param http
     *
     * @return
     *
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorize -> {
            // 放行静态资源，登录接口等
            // 被放行的接口上不能有权限注解例如@PreAuthorize ，否则放行将失效
            authorize.requestMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/favicon.ico",
                            "/assets/**", "/webjars/**", "/login", "/error").permitAll()
                    .anyRequest().authenticated();
        }).oauth2ResourceServer(oauth2 -> oauth2
                // 可在此处添加自定义解析设置
                .jwt(Customizer.withDefaults())
                // 添加未携带token或权限不足异常处理器
                .accessDeniedHandler(SecurityUtil::exceptionHandler)
                .authenticationEntryPoint(SecurityUtil::exceptionHandler));

        return http.build();
    }


    /**
     * 自定义JWT解析器，设置解析出来的权限信息的前缀y与再JWT中的key
     *
     * @return
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();

        // 设置权限信息前缀
        authoritiesConverter.setAuthorityPrefix("");
        // 设置权限信息在jwt claims中的key
        authoritiesConverter.setAuthoritiesClaimName(AuthConstants.AUTHORITIES_CLAIM_NAME);

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return jwtAuthenticationConverter;
    }


}
