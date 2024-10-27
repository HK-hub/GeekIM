package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName tb_operation_log
 */
@TableName(value ="tb_operation_log")
@Data
public class OperationLog implements Serializable {
    /**
     * 
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
     * 
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 
     */
    @TableField(value = "operate_type")
    private Integer operateType;

    /**
     * 
     */
    @TableField(value = "operation")
    private String operation;

    /**
     * 
     */
    @TableField(value = "request_id")
    private String requestId;

    /**
     * 
     */
    @TableField(value = "trace_id")
    private String traceId;

    /**
     * 
     */
    @TableField(value = "target_type")
    private String targetType;

    /**
     * 
     */
    @TableField(value = "target_id")
    private Long targetId;

    /**
     * 
     */
    @TableField(value = "description")
    private String description;

    /**
     * 
     */
    @TableField(value = "detail")
    private String detail;

    /**
     * 
     */
    @TableField(value = "ip_address")
    private String ipAddress;

    /**
     * 
     */
    @TableField(value = "user_agent")
    private String userAgent;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}