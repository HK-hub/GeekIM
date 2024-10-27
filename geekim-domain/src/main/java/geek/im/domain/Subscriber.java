package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 订阅者表
 * @TableName tb_subscriber
 */
@TableName(value ="tb_subscriber")
@Data
public class Subscriber implements Serializable {
    /**
     * 订阅者id
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
     * 对应用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 订阅内容，对象id
     */
    @TableField(value = "subscription_id")
    private Long subscriptionId;

    /**
     * 订阅日期
     */
    @TableField(value = "subscription_date")
    private LocalDateTime subscriptionDate;

    /**
     * 订阅类型：1.公众号，2.订阅号，3.机器人
     */
    @TableField(value = "type")
    private Integer type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}