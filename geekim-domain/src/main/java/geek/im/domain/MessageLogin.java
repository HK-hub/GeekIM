package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 登录消息
 * @TableName tb_message_login
 */
@TableName(value ="tb_message_login")
@Data
public class MessageLogin implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Integer id;

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
     * 消息id
     */
    @TableField(value = "message_id")
    private Long messageId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 登录IP地址
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * 登录平台[h5,ios,windows,mac,web]
     */
    @TableField(value = "platform")
    private String platform;

    /**
     * 登录设备信息
     */
    @TableField(value = "agent")
    private String agent;

    /**
     * 登录地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 登录异常原因
     */
    @TableField(value = "reason")
    private String reason;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}