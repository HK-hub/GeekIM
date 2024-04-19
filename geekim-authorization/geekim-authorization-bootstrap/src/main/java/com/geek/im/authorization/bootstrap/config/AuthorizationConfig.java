package com.geek.im.authorization.bootstrap.config;

import com.geek.im.authorization.bootstrap.authorization.DeviceClientAuthenticationConverter;
import com.geek.im.authorization.bootstrap.authorization.DeviceClientAuthenticationProvider;
import com.geek.im.authorization.bootstrap.customize.CustomOAuth2TokenCustomizer;
import com.geek.im.authorization.bootstrap.entrypoint.LoginTargetAuthenticationEntryPoint;
import com.geek.im.authorization.bootstrap.filter.CaptchaAuthenticationFilter;
import com.geek.im.authorization.bootstrap.grant.sms.SmsCaptchaGrantAuthenticationConverter;
import com.geek.im.authorization.bootstrap.grant.sms.SmsCaptchaGrantAuthenticationProvider;
import com.geek.im.authorization.bootstrap.handler.LoginFailureHandler;
import com.geek.im.authorization.bootstrap.handler.LoginSuccessHandler;
import com.geek.im.authorization.domain.constant.AuthConstants;
import com.geek.im.authorization.infrastructure.cache.RedisUtil;
import com.geek.im.authorization.infrastructure.utils.SecurityUtil;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.List;
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

    @Value("${spring.security.oauth2.authorizationserver.issuer:http://127.0.0.1:8080}")
    private String authorizationIssuer;

    @Value("${spring.security.oauth2.authorizationserver.custom.login.page.uri:http://127.0.0.1:5137}")
    private String customLoginUri;

    @Value("${spring.security.oauth2.authorizationserver.custom.cors.origin.allowed:http://127.0.0.1:5173}")
    private List<String> allowedCorsOriginList;

    @Resource
    private SecurityContextRepository securityContextRepository;

    @Resource
    private RedisUtil redisUtil;


    /**
     * 配置端点的过滤器链
     *
     * @param httpSecurity
     *
     * @return 过滤器链
     */
    @Bean
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity httpSecurity, RegisteredClientRepository registeredClientRepository,
                                                                      AuthorizationServerSettings authorizationServerSettings) throws Exception {

        // 配置默认的设置，忽略认证端点的csrf校验
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity);

        // 新建设备码Provider 和 Converter
        DeviceClientAuthenticationConverter deviceClientAuthenticationConverter = new DeviceClientAuthenticationConverter(authorizationServerSettings.getDeviceAuthorizationEndpoint());
        DeviceClientAuthenticationProvider deviceClientAuthenticationProvider = new DeviceClientAuthenticationProvider(registeredClientRepository);

        // 使用redis 存储读取登录的认证信息
        httpSecurity.securityContext(context -> context.securityContextRepository(this.securityContextRepository));

        httpSecurity.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                // 开启 OpenID Connect 1.0 协议相关端点
                .oidc(Customizer.withDefaults())
                // 设置自定义用户确认授权页
                .authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint.consentPage(AuthConstants.CUSTOM_CONSENT_PAGE_URI))
                // 设置设备码用户验证url:自定义用户验证页
                .deviceAuthorizationEndpoint(deviceAuthorizationEndpoint ->
                        deviceAuthorizationEndpoint.verificationUri(AuthConstants.CUSTOM_DEVICE_VERIFICATION_URI))
                // 设置验证设备码用户确认页面
                .deviceVerificationEndpoint(deviceVerificationEndpoint -> deviceVerificationEndpoint.consentPage(AuthConstants.CUSTOM_CONSENT_PAGE_URI))
                // 客户端认证添加设备码的converter和provider
                .clientAuthentication(clientAuthentication ->
                        clientAuthentication.authenticationConverter(deviceClientAuthenticationConverter).authenticationProvider(deviceClientAuthenticationProvider));


        // 当未登录时访问认证端点时重定向至登录页面
        httpSecurity.exceptionHandling(exceptions -> {
                    exceptions.defaultAuthenticationEntryPointFor(
                            new LoginTargetAuthenticationEntryPoint(this.customLoginUri),
                            new MediaTypeRequestMatcher(MediaType.TEXT_HTML));
                })
                // 处理使用access token访问用户信息端点和客户端注册端点
                .oauth2ResourceServer(resourceServer -> resourceServer.jwt(Customizer.withDefaults()));

        // 自定义短信验证码认证登录转换器
        SmsCaptchaGrantAuthenticationConverter smsCaptchaGrantAuthenticationConverter = new SmsCaptchaGrantAuthenticationConverter();
        // 自定义短信认证登录认证提供者
        SmsCaptchaGrantAuthenticationProvider smsCaptchaGrantAuthenticationProvider = new SmsCaptchaGrantAuthenticationProvider();
        httpSecurity.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                // 让认证服务器元数据中有自定义的认证方式
                .authorizationServerMetadataEndpoint(metadata ->
                        metadata.authorizationServerMetadataCustomizer(customizer -> customizer.grantType(AuthConstants.GRANT_TYPE_SMS_CODE)))
                // 添加自定义grant type: 短信认证登录
                .tokenEndpoint(tokenEndpoint -> tokenEndpoint.accessTokenRequestConverter(smsCaptchaGrantAuthenticationConverter)
                        .authenticationProvider(smsCaptchaGrantAuthenticationProvider));

        DefaultSecurityFilterChain filterChain = httpSecurity.build();

        // 从框架中获取provider所需的bean
        OAuth2TokenGenerator<?> tokenGenerator = httpSecurity.getSharedObject(OAuth2TokenGenerator.class);
        AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManager.class);
        OAuth2AuthorizationService authorizationService = httpSecurity.getSharedObject(OAuth2AuthorizationService.class);

        // 以上三个bean在build()方法之后调用是因为调用build方法时框架会尝试获取这些类，
        // 如果获取不到则初始化一个实例放入SharedObject中，所以要在build方法调用之后获取
        // 在通过set方法设置进provider中，但是如果在build方法之后调用authenticationProvider(provider)
        // 框架会提示unsupported_grant_type，因为已经初始化完了，在添加就不会生效了
        smsCaptchaGrantAuthenticationProvider.setOAuth2TokenGenerator(tokenGenerator)
                .setAuthenticationManager(authenticationManager).setOAuth2AuthorizationService(authorizationService);

        return filterChain;
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

        // 添加跨域过滤器
        http.addFilter(corsFilter());
        // 禁用csrf 与 cros
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(authorize -> {
                    // 放行静态资源
                    authorize.requestMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/favicon.ico",
                                    "/assets/**", "/webjars/**", "/login", AuthConstants.CUSTOM_CONSENT_PAGE_URI,
                                    "/verification/captcha/**", "/error")
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                })
                // 指定登录页面
                .formLogin(formLogin -> formLogin.loginPage(AuthConstants.UNIFIED_LOGIN_URI)
                        // 登录成功或失败改为相应json数据，不在进行重定向了
                        .successHandler(new LoginSuccessHandler())
                        .failureHandler(new LoginFailureHandler())
                );

        // 添加认证过滤器
        // 在UsernamePasswordAuthenticationFilter过滤器之前添加验证码校验过滤器，并且过滤post请求的登录接口
        // http.addFilterBefore(captchaAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // 添加 BearerTokenAuthenticationFilter,将认证服务当作一个资源服务，解析请求头中的token
        http.oauth2ResourceServer(resourceServer -> resourceServer
                .jwt(Customizer.withDefaults())
                .accessDeniedHandler(SecurityUtil::exceptionHandler)
                .authenticationEntryPoint(SecurityUtil::exceptionHandler));

        // 异常处理
        http.exceptionHandling(exception -> {
            // 当未登录访问认证端点时重定向至登录页面
            exception.defaultAuthenticationEntryPointFor(new LoginTargetAuthenticationEntryPoint(this.customLoginUri),
                    new MediaTypeRequestMatcher(MediaType.TEXT_HTML));
        });

        // 使用redis存储和获取登录认证信息
        http.securityContext(context -> context.securityContextRepository(this.securityContextRepository));

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
                // 自定义验证认证方式. 短信认证
                .authorizationGrantType(new AuthorizationGrantType(AuthConstants.GRANT_TYPE_SMS_CODE))
                // 授权码模式回调地址，OAuth2.1 已改为精确匹配，不能只设置域名，并且屏蔽了localhost
                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
                // 配置一个百度的域名回调，稍后使用该回调获取code
                .redirectUri("https://www.baidu.com")
                .redirectUri("http://127.0.0.1:7000/message/login/oauth2/code/messaging-client-oidc")
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
    public JWKSource<SecurityContext> jwkSource() throws ParseException {

        // 先从redis中获取
        String jwkSetCache = (String) this.redisUtil.get(AuthConstants.AUTHORIZATION_JWS_PREFIX_KEY);

        if (StringUtils.isNotEmpty(jwkSetCache)) {
            // 解析存储的jws
            JWKSet jwkSet = JWKSet.parse(jwkSetCache);
            return new ImmutableJWKSet<>(jwkSet);
        }

        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        // 生成JWS
        JWKSet jwkSet = new JWKSet(rsaKey);
        // 转为JSON字符串
        String jwtSetString = jwkSet.toString(Boolean.FALSE);
        // 存入redis
        this.redisUtil.set(AuthConstants.AUTHORIZATION_JWS_PREFIX_KEY, jwtSetString);
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
     * 认证服务器配置，设置JWT签发者地址，默认端点请求地址等
     *
     * @return
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {

        AuthorizationServerSettings authorizationServerSettings = AuthorizationServerSettings.builder()
                // 设置token签发地址：http(s)://{ip}:{port}/context-path, http(s)://domain.com/context-path
                // 如果需要通过ip访问这里就是ip，如果是有域名映射就填域名，通过什么方式访问该服务这里就填什么
                // 这里为ipconfig命令获取的测试IP地址
                .issuer(this.authorizationIssuer)
                .build();
        return authorizationServerSettings;
    }


    /**
     * 自定义JWT，将权限信息放入JWT中
     *
     * @return
     */
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> oAuth2TokenCustomizer() {

        return new CustomOAuth2TokenCustomizer();
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


    /**
     * 图形验证码检验过滤器
     *
     * @param redisUtil
     *
     * @return
     */
    // @Bean
    public CaptchaAuthenticationFilter captchaAuthenticationFilter(RedisUtil redisUtil) {

        CaptchaAuthenticationFilter captchaAuthenticationFilter = new CaptchaAuthenticationFilter(AuthConstants.UNIFIED_LOGIN_URI, redisUtil);
        return captchaAuthenticationFilter;
    }


    /**
     * 跨域过滤器配置
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {

        // 初始化cors配置对象
        CorsConfiguration configuration = new CorsConfiguration();

        // 设置允许跨域的域名，如果允许携带cookie的话，路径就不能写*号，*表示所有的域名都可以跨域访问
        for (String origin : this.allowedCorsOriginList) {
            configuration.addAllowedOrigin(origin);
        }
        // 设置跨域访问可以携带cookie
        configuration.setAllowCredentials(true);
        // 设置允许的请求方法
        configuration.addAllowedMethod("*");
        // 允许携带任何头信息
        configuration.addAllowedHeader("*");

        // 初始化cors配置源对象
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();

        // 给配置元对象设置过滤的参数
        // 参数一：过滤的路径，所有路径都要求检验是否跨域
        // 参数二：配置类
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);

        // 返回配置好的过滤器
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }


}
