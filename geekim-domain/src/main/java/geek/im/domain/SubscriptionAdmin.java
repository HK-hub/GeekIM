package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 订阅号-管理员表
 * @TableName tb_subscription_admin
 */
@TableName(value ="tb_subscription_admin")
@Data
public class SubscriptionAdmin implements Serializable {
    /**
     * 映射关系id
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
     * 用户id，对应管理员
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 订阅号，公众号id
     */
    @TableField(value = "subscription_id")
    private Long subscriptionId;

    /**
     * 订阅内容类型：1.订阅号，2.公众号
     */
    @TableField(value = "type")
    private Integer type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}