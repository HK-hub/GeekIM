package com.geek.im.authorization.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统菜单表
 *
 * @TableName sys_authority
 */
@TableName(value = "sys_authority")
@Data
public class SysAuthority implements Serializable {
    /**
     * 菜单自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 父菜单ID
     */
    @TableField(value = "menu_pid")
    private Integer menuPid;

    /**
     * 跳转URL
     */
    @TableField(value = "url")
    private String url;

    /**
     * 所需权限
     */
    @TableField(value = "authority")
    private String authority;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 0:菜单,1:接口
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 0:启用,1:删除
     */
    @TableField(value = "deleted")
    private Integer deleted;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user_id")
    private Long createUserId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}