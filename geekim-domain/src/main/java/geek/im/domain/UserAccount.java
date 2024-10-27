package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用户账户表
 * @TableName tb_user_account
 */
@TableName(value ="tb_user_account")
@Data
public class UserAccount implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "user_id")
    private Long userId;

    /**
     * 
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 
     */
    @TableField(value = "update_by")
    private Long updateBy;

    /**
     * 
     */
    @TableField(value = "deleted")
    private Boolean deleted;

    /**
     * 
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 
     */
    @TableField(value = "enable")
    private Boolean enable;

    /**
     * 
     */
    @TableField(value = "extra")
    private Object extra;

    /**
     * 
     */
    @TableField(value = "attribute")
    private Object attribute;

    /**
     * 用户名称
     */
    @TableField(value = "username")
    private String username;

    /**
     * QQ号
     */
    @TableField(value = "qq")
    private String qq;

    /**
     * 微信号
     */
    @TableField(value = "wechat")
    private String wechat;

    /**
     * GitHub账号
     */
    @TableField(value = "github")
    private String github;

    /**
     * gitee账号
     */
    @TableField(value = "gitee")
    private String gitee;

    /**
     * 钉钉账号
     */
    @TableField(value = "dingtalk")
    private String dingtalk;

    /**
     * 轻推账号
     */
    @TableField(value = "qingtui")
    private String qingtui;

    /**
     * openid
     */
    @TableField(value = "openid")
    private String openid;

    /**
     * 钱包余额
     */
    @TableField(value = "wallet")
    private Integer wallet;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}