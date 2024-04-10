package com.geek.im.authorization.bootstrap.customize;

import com.geek.im.authorization.domain.constant.AuthConstants;
import com.geek.im.authorization.domain.entity.Oauth2BasicUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : HK意境
 * @ClassName : CustomOAuth2TokenCustomizer
 * @date : 2024/3/27 19:07
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
public class CustomOAuth2TokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {


    /**
     * 自定义JWT包含的内容
     *
     * @param context the context containing the OAuth 2.0 Token attributes
     */
    @Override
    public void customize(JwtEncodingContext context) {
        // 检查登录用户信息是不是UserDetails，排除掉没有用户参与的流程
        if (context.getPrincipal().getPrincipal() instanceof UserDetails user) {
            // 获取申请的scopes
            Set<String> scopes = context.getAuthorizedScopes();
            // 获取用户的权限
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

            // 提取权限并转为字符串
            Set<String> authoritySet = Optional.ofNullable(authorities).orElse(Collections.emptyList()).stream()
                    // 获取权限字符串
                    .map(GrantedAuthority::getAuthority)
                    // 去重
                    .collect(Collectors.toSet());

            // 合并scope与用户信息
            authoritySet.addAll(scopes);

            JwtClaimsSet.Builder claims = context.getClaims();
            // 将权限信息放入jwt的claims中（也可以生成一个以指定字符分割的字符串放入）
            claims.claim(AuthConstants.AUTHORITIES_CLAIM_NAME, authoritySet);
            // 放入其它自定内容
            // 角色、头像...

            // 如果是自定义认证用户
            if (user instanceof Oauth2BasicUser basicUser) {
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
}
