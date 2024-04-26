package com.geek.im.authorization.interfaces.web;

import com.geek.im.authorization.domain.aggregate.Oauth2UserInfo;
import com.geek.im.authorization.domain.authority.CustomGrantedAuthority;
import com.geek.im.authorization.domain.constant.AuthConstants;
import com.geek.im.authorization.domain.entity.Oauth2BasicUser;
import com.geek.im.authorization.domain.entity.Oauth2ThirdAccount;
import com.geek.im.authorization.domain.service.Oauth2BasicUserService;
import com.geek.im.authorization.domain.service.Oauth2ThirdAccountService;
import com.geek.im.authorization.infrastructure.assembly.OauthBasicUserConverter;
import com.geek.im.common.response.ResponseResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : HK意境
 * @ClassName : UserInfoController
 * @date : 2024/3/23 22:01
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user/info")
public class UserInfoController {

    @Resource
    private Oauth2BasicUserService oauth2BasicUserService;
    @Resource
    private Oauth2ThirdAccountService oauth2ThirdAccountService;


    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/detail")
    public ResponseResult<Oauth2UserInfo> getUserInfo(Principal principal) {

        Oauth2UserInfo userinfo = new Oauth2UserInfo();

        if (!(principal instanceof JwtAuthenticationToken jwtAuthenticationToken)) {
            return ResponseResult.SUCCESS(userinfo);
        }

        // 获取 JWT解析内容
        Jwt jwt = jwtAuthenticationToken.getToken();

        // 获取当前用户的账号
        String account = jwt.getClaim(JwtClaimNames.SUB);
        // 获取scope
        List<String> scopes = jwt.getClaimAsStringList(AuthConstants.SCOPE_CLAIM_NAME);
        List<String> authorities = jwt.getClaimAsStringList(AuthConstants.AUTHORITIES_CLAIM_NAME);
        if (CollectionUtils.isNotEmpty(authorities)) {
            scopes = null;
        }

        // 查询绑定的基础用户
        Oauth2BasicUser basicUser = this.oauth2BasicUserService.getUserByAccount(account);
        if (Objects.nonNull(basicUser)) {
            // 填充用户权限信息
            this.fillUserAuthority(authorities, basicUser, scopes);
            userinfo = OauthBasicUserConverter.INSTANCE.toUserInfo(basicUser);
            // 根据用户信息查询第三方登录信
            Oauth2ThirdAccount thirdAccount = this.oauth2ThirdAccountService.getThirdAccountByUserId(basicUser.getId());
            if (Objects.isNull(thirdAccount)) {
                return ResponseResult.SUCCESS(userinfo);
            }

            // 设置三方账户信息
            userinfo.setCredentials(thirdAccount.getCredentials())
                    .setCredentialsExpiresAt(thirdAccount.getCredentialsExpiresAt());
            userinfo.setLocation(thirdAccount.getLocation());
            userinfo.setThirdUsername(thirdAccount.getThirdUsername());
            return ResponseResult.SUCCESS(userinfo);
        }

        // 基础用户信息为空
        // 根据当前sub去三方登录表去查
        String provider = jwt.getClaim(AuthConstants.LOGIN_TYPE_PARAMETER_NAME);
        Oauth2ThirdAccount thirdAccount = this.oauth2ThirdAccountService.getThirdAccountByProvider(account, provider);

        // 三方账户不存在
        if (Objects.isNull(thirdAccount)) {
            return ResponseResult.SUCCESS(userinfo);
        }

        // 查到之后反查基础用户表
        Oauth2BasicUser oauth2BasicUser = this.oauth2BasicUserService.getById(thirdAccount.getUserId());

        // 填充用户的权限信息
        this.fillUserAuthority(authorities, oauth2BasicUser, scopes);
        // 复制基础用户信息
        userinfo = OauthBasicUserConverter.INSTANCE.toUserInfo(basicUser);

        // 设置三方用户信息
        userinfo.setLocation(thirdAccount.getLocation());
        userinfo.setCredentials(thirdAccount.getCredentials());
        userinfo.setThirdUsername(thirdAccount.getThirdUsername());
        userinfo.setCredentialsExpiresAt(thirdAccount.getCredentialsExpiresAt());

        return ResponseResult.SUCCESS(userinfo);
    }


    /**
     * 设置用户权限信息
     *
     * @param authorities
     * @param oauth2BasicUser
     * @param scopes
     */
    private void fillUserAuthority(List<String> authorities, Oauth2BasicUser oauth2BasicUser, List<String> scopes) {

        authorities.addAll(scopes);
        Set<String> authoritySet = new HashSet<>(authorities);

        Set<CustomGrantedAuthority> grantedAuthoritySet = authoritySet.stream().map(CustomGrantedAuthority::new)
                .collect(Collectors.toSet());

        oauth2BasicUser.setAuthorities(grantedAuthoritySet);
    }


}
