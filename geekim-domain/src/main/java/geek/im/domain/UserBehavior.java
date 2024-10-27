package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用户行为表
 * @TableName tb_user_behavior
 */
@TableName(value ="tb_user_behavior")
@Data
public class UserBehavior implements Serializable {
    /**
     * 主键ID
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
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 行为类型:发送消息等
     */
    @TableField(value = "behavior_type")
    private Integer behaviorType;

    /**
     * 行为时间
     */
    @TableField(value = "behavior_time")
    private LocalDateTime behaviorTime;

    /**
     * 行为详情
     */
    @TableField(value = "behavior_detail")
    private String behaviorDetail;

    /**
     * 违反类型
     */
    @TableField(value = "violation_type")
    private Integer violationType;

    /**
     * 负载类型:消息等
     */
    @TableField(value = "payload_type")
    private Integer payloadType;

    /**
     * 负载ID：数据实体ID
     */
    @TableField(value = "payload_id")
    private Long payloadId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}