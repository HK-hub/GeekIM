package com.geek.im.authorization.bootstrap.federation;

import com.geek.im.authorization.domain.constant.AuthConstants;
import com.geek.im.authorization.domain.entity.Oauth2BasicUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : HK意境
 * @ClassName : FederatedIdentityIdTokenCustomizer
 * @date : 2024/4/23 18:37
 * @description : 将登录时用户信息写入IdToken中
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class FederatedIdentityIdTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    private static final Set<String> ID_TOKEN_CLAIMS = Set.of(
            IdTokenClaimNames.ISS,
            IdTokenClaimNames.SUB,
            IdTokenClaimNames.AUD,
            IdTokenClaimNames.EXP,
            IdTokenClaimNames.IAT,
            IdTokenClaimNames.AUTH_TIME,
            IdTokenClaimNames.NONCE,
            IdTokenClaimNames.ACR,
            IdTokenClaimNames.AMR,
            IdTokenClaimNames.AZP,
            IdTokenClaimNames.AT_HASH,
            IdTokenClaimNames.C_HASH
    );


    @Override
    public void customize(JwtEncodingContext context) {

        // OidcUser类型
        if (OidcParameterNames.ID_TOKEN.equals(context.getTokenType().getValue())) {
            // 是IdToken类型
            Map<String, Object> thirdPartyClaims = this.extractClaims(context.getPrincipal());

            context.getClaims().claims(existingClaims -> {
                // 移除冲突的第三方认证服务器设置的claim
                existingClaims.keySet().forEach(thirdPartyClaims::remove);

                // Remove standard id_token claims that could cause problems with clients
                ID_TOKEN_CLAIMS.forEach(thirdPartyClaims::remove);

                // 加入第三方其他的claims
                existingClaims.putAll(thirdPartyClaims);
            });
        }


        // 检查登录用户信息是不是OAuth2User,在token中添加loginType属性
        if (context.getPrincipal().getPrincipal() instanceof OAuth2User user) {
            JwtClaimsSet.Builder claims = context.getClaims();
            // 获取登录方式
            Object loginType = user.getAttribute(AuthConstants.LOGIN_TYPE_PARAMETER_NAME);
            if (loginType instanceof String) {
                claims.claim(AuthConstants.LOGIN_TYPE_PARAMETER_NAME, loginType);
            }
        }

        // 检查登录用户信息是不是UserDetails,排除掉没有用户参与的流程
        if (context.getPrincipal().getPrincipal() instanceof UserDetails userDetails) {
            // 获取申请的scopes
            Set<String> authorizedScopes = context.getAuthorizedScopes();
            // 获取用户的权限
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

            // 提取权限并转为字符串
            Set<String> authoritySet = Optional.ofNullable(authorities).orElse(Collections.emptyList()).stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());

            // 合并scope与用户权限信息
            authoritySet.addAll(authorizedScopes);

            // 将权限信息放入JWT中
            JwtClaimsSet.Builder claims = context.getClaims();
            claims.claim(AuthConstants.AUTHORITIES_KEY, authoritySet);
            // 放入其它自定内容
            // 角色、头像...

            // 如果是自定义认证用户
            if (userDetails instanceof Oauth2BasicUser basicUser) {
                // 放入角色，头像等信息
                claims.claim(AuthConstants.ROLES_CLAIM_NAME, basicUser.getRoles());
                String avatarUrl = basicUser.getAvatarUrl();
                if (StringUtils.isEmpty(avatarUrl)) {
                    // 头像为空
                    avatarUrl = "https://p26-passport.byteacctimg.com/img/user-avatar/2e822e5bf3013896d2ab51ba1e015115~50x50.awebp";
                }
                claims.claim(AuthConstants.AVATAR_CLAIM_NAME, avatarUrl);
            }

        }
    }


    private Map<String, Object> extractClaims(Authentication principal) {

        Map<String, Object> claims = Collections.emptyMap();
        if (principal.getPrincipal() instanceof OidcUser oidcUser) {

            OidcIdToken idToken = oidcUser.getIdToken();
            claims = idToken.getClaims();
        } else if (principal.getPrincipal() instanceof OAuth2User oAuth2User) {

            claims = oAuth2User.getAttributes();
        }

        return new HashMap<>(claims);
    }
}
