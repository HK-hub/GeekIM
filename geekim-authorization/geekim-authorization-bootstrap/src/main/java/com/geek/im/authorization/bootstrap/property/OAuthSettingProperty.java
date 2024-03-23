package com.geek.im.authorization.bootstrap.property;

import lombok.Data;

/**
 * @author : HK意境
 * @ClassName : OAuthSettingProperty
 * @date : 2024/3/23 14:44
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
// @Component
// @ConfigurationProperties(prefix = "spring.security.oauth2.authorizationserver.endpoint")
public class OAuthSettingProperty {

    // 启用自定义端点
    private String handlerPath = "";

    private String authorizationUri = "/oauth2/authorize";
    private String deviceAuthorizationUri = "/oauth2/device_authorization";
    private String deviceVerificationUri = "/oauth2/device_verification";
    private String tokenUri = "/oauth2/token";
    private String jwkSetUri = "/oauth2/jwks";
    private String tokenRevocationUri = "/oauth2/revoke";
    private String tokenIntrospectionUri = "/oauth2/introspect";

    // 登录端点
    private String loginProcessingUri = "/login";

    private String consentPageUri = "/oauth2/consent";

    private String logoutUri = "/connect/logout";
    private String clientRegistrationUri = "/connect/register";
    private String userInfoUri = "/userinfo";


}
