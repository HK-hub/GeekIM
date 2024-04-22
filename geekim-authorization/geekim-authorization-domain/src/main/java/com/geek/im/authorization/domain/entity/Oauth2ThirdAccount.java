package com.geek.im.authorization.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 三方登录账户信息表
 *
 * @TableName oauth2_third_account
 */
@TableName(value = "oauth2_third_account")
@Data
public class Oauth2ThirdAccount implements Serializable {
    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户表主键
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 三方登录唯一id
     */
    @TableField(value = "unique_id")
    private String uniqueId;

    /**
     * 三方用户账号
     */
    @TableField(value = "third_username")
    private String thirdUsername;


    /**
     * 三方登录获取的认证信息(token)
     */
    @TableField(value = "credentials")
    private String credentials;


    /**
     * 三方登录获取的认证信息过期时间
     */
    @TableField(value = "credentials_expires_at")
    private LocalDateTime credentialsExpiresAt;


    /**
     * 三方登录类型
     */
    @TableField(value = "`type`")
    private String type;

    /**
     * 博客地址
     */
    @TableField(value = "blog")
    private String blog;

    /**
     * 地址
     */
    @TableField(value = "location")
    private String location;


    /**
     * 用户名、昵称
     */
    @TableField("`name`")
    private String name;

    /**
     * 头像地址
     */
    @TableField("avatar")
    private String avatar;


    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}