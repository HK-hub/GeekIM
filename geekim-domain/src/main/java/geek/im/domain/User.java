package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用户表
 * @TableName tb_user
 */
@TableName(value ="tb_user")
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "id")
    private Long id;

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
     * 用户名，昵称
     */
    @TableField(value = "username")
    private String username;

    /**
     * 账号，类比QQ号,唯一性
     */
    @TableField(value = "account")
    private String account;

    /**
     * 加密后的密码,盐值为原密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 大陆手机号,唯一性，一个手机只能绑定一个账号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱,唯一性,一个邮箱只能绑定一个账号
     */
    @TableField(value = "email")
    private String email;

    /**
     * 用户大头像
     */
    @TableField(value = "big_avatar")
    private String bigAvatar;

    /**
     * 用户头像缩略图
     */
    @TableField(value = "mini_avatar")
    private String miniAvatar;

    /**
     * 
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 用户二维码
     */
    @TableField(value = "qrcode")
    private String qrcode;

    /**
     * 客户端id:用于安卓设备等
     */
    @TableField(value = "client_id")
    private String clientId;

    /**
     * 是否机器人，以及对应的机器人id
     */
    @TableField(value = "robot_id")
    private Long robotId;

    /**
     * ip地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * mac地址网卡地址
     */
    @TableField(value = "mac")
    private String mac;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}