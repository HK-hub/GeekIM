package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 系统登录信息
 * @TableName tb_system_login_info
 */
@TableName(value ="tb_system_login_info")
@Data
public class SystemLoginInfo implements Serializable {
    /**
     * 登录信息id
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
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 登录IP地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 登录定位位置
     */
    @TableField(value = "location")
    private String location;

    /**
     * 浏览器
     */
    @TableField(value = "browser")
    private String browser;

    /**
     * 操作系统
     */
    @TableField(value = "os")
    private String os;

    /**
     * user_agent
     */
    @TableField(value = "user_agent")
    private String userAgent;

    /**
     * 来源地址
     */
    @TableField(value = "refer")
    private String refer;

    /**
     * 登录消息提示
     */
    @TableField(value = "message")
    private String message;

    /**
     * 客户端标识
     */
    @TableField(value = "client_id")
    private String clientId;

    /**
     * mac地址
     */
    @TableField(value = "mac_address")
    private String macAddress;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}