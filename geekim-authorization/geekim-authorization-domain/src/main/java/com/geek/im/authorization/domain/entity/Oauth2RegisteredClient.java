package com.geek.im.authorization.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName oauth2_registered_client
 */
@TableName(value = "oauth2_registered_client")
@Data
public class Oauth2RegisteredClient implements Serializable {
    /**
     *
     */
    @TableId(value = "id")
    private String id;

    /**
     *
     */
    @TableField(value = "client_id")
    private String clientId;

    /**
     *
     */
    @TableField(value = "client_id_issued_at")
    private Date clientIdIssuedAt;

    /**
     *
     */
    @TableField(value = "client_secret")
    private String clientSecret;

    /**
     *
     */
    @TableField(value = "client_secret_expires_at")
    private Date clientSecretExpiresAt;

    /**
     *
     */
    @TableField(value = "client_name")
    private String clientName;

    /**
     *
     */
    @TableField(value = "client_authentication_methods")
    private String clientAuthenticationMethods;

    /**
     *
     */
    @TableField(value = "authorization_grant_types")
    private String authorizationGrantTypes;

    /**
     *
     */
    @TableField(value = "redirect_uris")
    private String redirectUris;

    /**
     *
     */
    @TableField(value = "post_logout_redirect_uris")
    private String postLogoutRedirectUris;

    /**
     *
     */
    @TableField(value = "scopes")
    private String scopes;

    /**
     *
     */
    @TableField(value = "client_settings")
    private String clientSettings;

    /**
     *
     */
    @TableField(value = "token_settings")
    private String tokenSettings;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}