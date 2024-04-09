package com.geek.im.authorization.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
     * 三方登录类型
     */
    @TableField(value = "type")
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
     * 绑定时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}