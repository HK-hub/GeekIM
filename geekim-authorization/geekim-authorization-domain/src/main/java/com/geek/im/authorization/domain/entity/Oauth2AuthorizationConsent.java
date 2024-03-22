package com.geek.im.authorization.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName oauth2_authorization_consent
 */
@TableName(value = "oauth2_authorization_consent")
@Data
public class Oauth2AuthorizationConsent implements Serializable {
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
     *
     */
    @TableField(value = "authorities")
    private String authorities;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}