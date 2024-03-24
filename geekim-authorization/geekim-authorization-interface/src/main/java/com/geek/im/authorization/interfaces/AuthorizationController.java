package com.geek.im.authorization.interfaces;

import com.geek.im.authorization.domain.value.ScopeWithDescription;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author : HK意境
 * @ClassName : AuthorizationController
 * @date : 2024/3/21 20:48
 * @description : 认证服务器相关接口
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Controller
// @RequestMapping("/authorization")
@RequiredArgsConstructor
public class AuthorizationController {

    private final RegisteredClientRepository registeredClientRepository;

    private final OAuth2AuthorizationConsentService authorizationConsentService;


    /**
     * 请求转发至自定义的登录页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }


    /**
     * 授权确认页面
     *
     * @param principal
     * @param model
     * @param clientId
     * @param scope
     * @param state
     * @param userCode
     *
     * @return
     */
    @GetMapping("/oauth2/consent")
    public String consent(Principal principal, Model model,
                          @RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId,
                          @RequestParam(OAuth2ParameterNames.SCOPE) String scope,
                          @RequestParam(OAuth2ParameterNames.STATE) String state,
                          @RequestParam(value = OAuth2ParameterNames.USER_CODE, required = false) String userCode) {

        // 移除掉之前已经授权的范围
        Set<String> scopesToApprove = new HashSet<>();
        Set<String> previouslyApprovedScopes = new HashSet<>();
        this.registeredClientRepository.findByClientId("messaging-client");
        RegisteredClient client = this.registeredClientRepository.findByClientId(clientId);
        if (client == null) {
            throw new RuntimeException("客户端不存在");
        }

        OAuth2AuthorizationConsent consent = this.authorizationConsentService.findById(client.getId(), principal.getName());
        Set<String> authorizedScopes;
        if (consent != null) {
            authorizedScopes = consent.getScopes();
        } else {
            authorizedScopes = Collections.emptySet();
        }

        for (String requestedScope : StringUtils.delimitedListToStringArray(scope, " ")) {
            if (OidcScopes.OPENID.equals(requestedScope)) {
                continue;
            }
            if (authorizedScopes.contains(requestedScope)) {
                // 之前已经进行了授权
                previouslyApprovedScopes.add(requestedScope);
            } else {
                // 没有进行授权的范围
                scopesToApprove.add(requestedScope);
            }
        }

        model.addAttribute("clientId", clientId);
        model.addAttribute("state", state);
        model.addAttribute("scopes", ScopeWithDescription.withDescription(scopesToApprove));
        model.addAttribute("previouslyApprovedScopes", ScopeWithDescription.withDescription(previouslyApprovedScopes));
        model.addAttribute("principalName", principal.getName());
        model.addAttribute("userCode", userCode);

        if (StringUtils.hasText(userCode)) {
            // 设备码模式
            model.addAttribute(OAuth2ParameterNames.SCOPE, scope);
            model.addAttribute("requestURI", "/oauth2/device_verification");
        } else {
            // 授权码模式
            model.addAttribute("requestURI", "/oauth2/authorize");
        }

        // 请求转发到自定义的授权确认页面
        return "consent";
    }


    /**
     * 激活页面
     *
     * @param userCode
     *
     * @return
     */
    @GetMapping("/activate")
    public String activate(@RequestParam(value = "user_code", required = false) String userCode) {

        if (Objects.nonNull(userCode)) {
            // 请求转发.进行设备验证
            return "redirect:/oauth2/device_verification?user_code=" + userCode;
        }
        // 没有userCode转发到激活页面
        return "device-activate";
    }


    /**
     * 设备已成功激活
     *
     * @return
     */
    @GetMapping("/activated")
    public String activated() {
        return "device-activated";
    }


    @GetMapping(value = "/", params = "success")
    public String success() {
        return "device-activated";
    }


}
