package com.geek.im.authorization.bootstrap.config;

import com.geek.im.authorization.domain.constant.AuthConstants;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Objects;
import java.util.UUID;

/**
 * @author : HK意境
 * @ClassName : AuthorizationConfig
 * @date : 2024/3/20 20:08
 * @description :
 * 认证配置
 * {@link EnableMethodSecurity} 开启全局方法认证，启用JSR250注解支持，启用注解 {@link Secured} 支持，
 * 在Spring Security 6.0版本中将@Configuration注解从@EnableWebSecurity, @EnableMethodSecurity, @EnableGlobalMethodSecurity
 * 和 @EnableGlobalAuthentication 中移除，使用这些注解需手动添加 @Configuration 注解
 * {@link EnableWebSecurity} 注解有两个作用:
 * 1. 加载了WebSecurityConfiguration配置类, 配置安全认证策略。
 * 2. 加载了AuthenticationConfiguration, 配置了认证信息。
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class AuthorizationConfig {


    /**
     * 配置端点的过滤器链
     *
     * @param httpSecurity
     *
     * @return 过滤器链
     */
    @Bean
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // 配置默认的设置，忽略认证端点的csrf校验
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity);
        httpSecurity.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                // 开启 OpenID Connect 1.0 协议相关端点
                .oidc(Customizer.withDefaults())
                // 设置自定义用户确认授权页
                .authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint.consentPage(AuthConstants.CUSTOM_CONSENT_PAGE_URI));
        // 当未登录时访问认证端点时重定向至登录页面
        httpSecurity.exceptionHandling(exceptions -> {
                    exceptions.defaultAuthenticationEntryPointFor(new LoginUrlAuthenticationEntryPoint(AuthConstants.UNIFIED_LOGIN_URI),
                            new MediaTypeRequestMatcher(MediaType.TEXT_HTML));
                })
                // 处理使用access token访问用户信息端点和客户端注册端点
                .oauth2ResourceServer(resourceServer -> resourceServer.jwt(Customizer.withDefaults()));

        return httpSecurity.build();
    }


    /**
     * 配置认证相关的过滤器链
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
                    // 放行静态资源
                    authorize.requestMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/favicon.ico",
                                    "/assets/**", "/webjars/**", "/login", AuthConstants.CUSTOM_CONSENT_PAGE_URI)
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                })
                // 指定登录页面
                .formLogin(formLogin -> formLogin.loginPage(AuthConstants.UNIFIED_LOGIN_URI));
        // 添加 BearerTokenAuthenticationFilter,将认证服务当作一个资源服务，解析请求头中的token
        http.oauth2ResourceServer(resourceServer -> resourceServer.jwt(Customizer.withDefaults()));

        return http.build();
    }


    /**
     * 配置密码编码器
     * 配置密码解析器，使用BCrypt的方式对密码进行加密和验证
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


    /**
     * 配置客户端repository
     *
     * @return
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {

        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                // 客户端id
                .clientId("messaging-client")
                // 客户端密钥，使用密码解析器加密
                .clientSecret(passwordEncoder.encode(AuthConstants.CLIENT_SECRET))
                // 客户端认证方式，基于请求头的认证
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                // 配置资源服务器使用该客户端获取授权时支持的授权类型
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                // 授权码模式回调地址，OAuth2.1 已改为精确匹配，不能只设置域名，并且屏蔽了localhost
                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
                // 配置一个百度的域名回调，稍后使用该回调获取code
                .redirectUri("https://www.baidu.com")
                // 客户端的授权范围: OpenId和Profile是IdToken的Scope, 获取授权时请求openId的scope时，认证服务会返回IdToken
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                // 自定义scope
                .scope("message.read")
                .scope("message.write")
                // 客户端设置，设置用户需要确认授权
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();

        // 基于db存储客户端，还有一个基于内存的实现 InMemoryRegisteredClientRepository
        JdbcRegisteredClientRepository registeredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);

        // 初始化客户端
        RegisteredClient repositoryByClientId = registeredClientRepository.findByClientId(registeredClient.getClientId());
        if (Objects.isNull(repositoryByClientId)) {
            registeredClientRepository.save(registeredClient);
        }

        // 设备码授权客户端
        RegisteredClient deviceClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("device-message-client")
                // 公共客户端
                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
                // 设备码授权
                .authorizationGrantType(AuthorizationGrantType.DEVICE_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                // 自定义scope
                .scope("message.read")
                .scope("message.write")
                .build();
        RegisteredClient deviceClientByRepository = registeredClientRepository.findByClientId(deviceClient.getClientId());
        if (Objects.isNull(deviceClientByRepository)) {
            registeredClientRepository.save(deviceClient);
        }


        // PKCE 客户端的
        RegisteredClient pkceClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("pkce-message-client")
                // 公共客户端
                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
                // 设备码授权
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                // 授权码模式回调地址
                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
                // 启用授权码扩展模式
                .clientSettings(ClientSettings.builder().requireProofKey(true).build())
                // 自定义scope
                .scope("message.read")
                .scope("mesage.write")
                .build();
        RegisteredClient findPkceClient = registeredClientRepository.findByClientId(pkceClient.getClientId());
        if (Objects.isNull(findPkceClient)) {
            registeredClientRepository.save(pkceClient);
        }

        return registeredClientRepository;
    }


    /**
     * 配置基于DB的OAuth2授权管理服务
     *
     * @param jdbcTemplate               db数据源信息
     * @param registeredClientRepository 上边注入的客户端repository
     *
     * @return
     */
    @Bean
    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {

        // 基于DB的OAuth2认证服务，还有一个基于内存的服务InMemoryOAuth2AuthorizationService
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }


    /**
     * 配置授权确认管理服务
     * 配置基于db的授权确认管理服务
     *
     * @param jdbcTemplate
     * @param registeredClientRepository
     *
     * @return
     */
    @Bean
    public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {

        // 基于DB的OAuth2授权同意服务，还有一个基于内存的服务InMemoryOAuth2AuthorizationConsentService
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }


    /**
     * 配置JWK源，使用非对称加密算法，公开用于检索匹配指定选择器的JWK的方法
     *
     * @return
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() {

        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }


    /**
     * 生成rsa密钥对，提供给jwk
     *
     * @return 密钥对
     */
    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }


    /**
     * 配置JWT解析器
     *
     * @param jwkSource jwk源
     *
     * @return
     */
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {

        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }


    /**
     * 配置认证服务器设置
     * 配置认证授权相关 URI链接
     *
     * @return
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {

        AuthorizationServerSettings.Builder builder = AuthorizationServerSettings.builder();

        return builder.build();
    }


    /**
     * 先暂时配置一个基于内存的用户，框架在调用时会默认使用
     * {@link UserDetailsService#loadUserByUsername(String)} 方法
     * 根据账号查询用户信息，一般重写该方法实现自己的业务逻辑
     *
     * @param passwordEncoder 密码解析器
     *
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        UserDetails userDetails = User.withUsername("admin")
                .password(passwordEncoder.encode("123456"))
                .roles("admin", "normal")
                .authorities("app", "web")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }

}
