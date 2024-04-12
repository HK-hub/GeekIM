package com.geek.im.authorization.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.geek.im.authorization.domain.authority.CustomGrantedAuthority;
import lombok.Data;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 基础用户信息表
 *
 * @TableName oauth2_basic_user
 */
@TableName(value = "oauth2_basic_user")
@Data
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class Oauth2BasicUser implements UserDetails, Serializable {
    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名、昵称
     */
    @TableField(value = "username")
    private String username;

    /**
     * 账号
     */
    @TableField(value = "account")
    private String account;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 头像地址
     */
    @TableField(value = "avatar_url")
    private String avatarUrl;

    /**
     * 是否已删除
     */
    @TableField(value = "deleted")
    private Boolean deleted;

    /**
     * 用户来源
     */
    @TableField(value = "source_from")
    private String sourceFrom;

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


    /**
     * 权限信息
     */
    @TableField(exist = false)
    private Collection<CustomGrantedAuthority> authorities;

    /**
     * 角色信息
     */
    @TableField(exist = false)
    private Collection<? extends SysRole> roles = new ArrayList<>();


    @Override
    public Collection<CustomGrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.account;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return BooleanUtils.isFalse(this.deleted);
    }
}