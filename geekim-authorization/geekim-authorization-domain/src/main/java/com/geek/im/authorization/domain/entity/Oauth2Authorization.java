package com.geek.im.authorization.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName oauth2_authorization
 */
@TableName(value = "oauth2_authorization")
@Data
public class Oauth2Authorization implements Serializable {
    /**
     *
     */
    @TableId(value = "id")
    private String id;

    /**
     *
     */
    @TableField(value = "registered_client_id")
    private String registeredClientId;

    /**
     *
     */
    @TableField(value = "principal_name")
    private String principalName;

    /**
     * 授权类型
     */
    @TableField(value = "authorization_grant_type")
    private String authorizationGrantType;

    /**
     * 授权范围
     */
    @TableField(value = "authorized_scopes")
    private String authorizedScopes;

    /**
     * 认证状态
     */
    @TableField(value = "state")
    private String state;

    /**
     * 授权码签发时间
     */
    @TableField(value = "authorization_code_issued_at")
    private Date authorizationCodeIssuedAt;

    /**
     * 授权码过期时间
     */
    @TableField(value = "authorization_code_expires_at")
    private Date authorizationCodeExpiresAt;

    /**
     * 访问令牌签发时间
     */
    @TableField(value = "access_token_issued_at")
    private Date accessTokenIssuedAt;

    /**
     * 访问令牌过期时间
     */
    @TableField(value = "access_token_expires_at")
    private Date accessTokenExpiresAt;

    /**
     * 访问令牌类型
     */
    @TableField(value = "access_token_type")
    private String accessTokenType;

    /**
     * 访问令牌范围
     */
    @TableField(value = "access_token_scopes")
    private String accessTokenScopes;

    /**
     *
     */
    @TableField(value = "oidc_id_token_issued_at")
    private Date oidcIdTokenIssuedAt;

    /**
     *
     */
    @TableField(value = "oidc_id_token_expires_at")
    private Date oidcIdTokenExpiresAt;

    /**
     * 刷新令牌签发时间
     */
    @TableField(value = "refresh_token_issued_at")
    private Date refreshTokenIssuedAt;

    /**
     * 刷新令牌过期时间
     */
    @TableField(value = "refresh_token_expires_at")
    private Date refreshTokenExpiresAt;

    /**
     * 用户编码签发时间
     */
    @TableField(value = "user_code_issued_at")
    private Date userCodeIssuedAt;

    /**
     * 用户编码过期时间
     */
    @TableField(value = "user_code_expires_at")
    private Date userCodeExpiresAt;

    /**
     * 设备码签发时间
     */
    @TableField(value = "device_code_issued_at")
    private Date deviceCodeIssuedAt;

    /**
     * 设备码过期时间
     */
    @TableField(value = "device_code_expires_at")
    private Date deviceCodeExpiresAt;

    /**
     * 认证信息
     */
    @TableField(value = "attributes")
    private byte[] attributes;

    /**
     * 授权码
     */
    @TableField(value = "authorization_code_value")
    private byte[] authorizationCodeValue;

    /**
     * 授权码元数据
     */
    @TableField(value = "authorization_code_metadata")
    private byte[] authorizationCodeMetadata;

    /**
     * 访问令牌
     */
    @TableField(value = "access_token_value")
    private byte[] accessTokenValue;

    /**
     * 访问令牌元数据
     */
    @TableField(value = "access_token_metadata")
    private byte[] accessTokenMetadata;

    /**
     *
     */
    @TableField(value = "oidc_id_token_value")
    private byte[] oidcIdTokenValue;

    /**
     *
     */
    @TableField(value = "oidc_id_token_metadata")
    private byte[] oidcIdTokenMetadata;

    /**
     * 刷新令牌
     */
    @TableField(value = "refresh_token_value")
    private byte[] refreshTokenValue;

    /**
     * 刷新令牌元数据
     */
    @TableField(value = "refresh_token_metadata")
    private byte[] refreshTokenMetadata;

    /**
     * 用户认证信息
     */
    @TableField(value = "user_code_value")
    private byte[] userCodeValue;

    /**
     * 用户编码元数据
     */
    @TableField(value = "user_code_metadata")
    private byte[] userCodeMetadata;

    /**
     * 设备编码
     */
    @TableField(value = "device_code_value")
    private byte[] deviceCodeValue;

    /**
     * 设备码元数据
     */
    @TableField(value = "device_code_metadata")
    private byte[] deviceCodeMetadata;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}