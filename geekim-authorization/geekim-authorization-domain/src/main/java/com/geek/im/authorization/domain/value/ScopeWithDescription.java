package com.geek.im.authorization.domain.value;

import lombok.Data;
import org.springframework.security.oauth2.core.oidc.OidcScopes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class ScopeWithDescription {
    private static final String DEFAULT_DESCRIPTION = "UNKNOWN SCOPE - We cannot provide information about this permission, use caution when granting this.";
    private static final Map<String, String> scopeDescriptions = new HashMap<>();

    static {
        scopeDescriptions.put(
                OidcScopes.PROFILE,
                "This application will be able to read your profile information."
        );
        scopeDescriptions.put(
                "message.read",
                "This application will be able to read your message."
        );
        scopeDescriptions.put(
                "message.write",
                "This application will be able to add new messages. It will also be able to edit and delete existing messages."
        );
        scopeDescriptions.put(
                "other.scope",
                "This is another scope example of a scope description."
        );
    }

    public final String scope;
    public final String description;

    ScopeWithDescription(String scope) {
        this.scope = scope;
        this.description = scopeDescriptions.getOrDefault(scope, DEFAULT_DESCRIPTION);
    }


    public static Set<ScopeWithDescription> withDescription(Set<String> scopes) {
        Set<ScopeWithDescription> scopeWithDescriptions = new HashSet<>();
        for (String scope : scopes) {
            scopeWithDescriptions.add(new ScopeWithDescription(scope));

        }
        return scopeWithDescriptions;
    }


}