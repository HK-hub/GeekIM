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
 * @TableName tb_trace_log
 */
@TableName(value ="tb_trace_log")
@Data
public class TraceLog implements Serializable {
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
    @TableField(value = "trace_id")
    private String traceId;

    /**
     * 
     */
    @TableField(value = "span_id")
    private String spanId;

    /**
     * 
     */
    @TableField(value = "parent_span_id")
    private String parentSpanId;

    /**
     * 
     */
    @TableField(value = "request_id")
    private String requestId;

    /**
     * 
     */
    @TableField(value = "service_name")
    private String serviceName;

    /**
     * 
     */
    @TableField(value = "server_id")
    private String serverId;

    /**
     * 
     */
    @TableField(value = "method")
    private String method;

    /**
     * 
     */
    @TableField(value = "url")
    private String url;

    /**
     * 
     */
    @TableField(value = "parameter")
    private Object parameter;

    /**
     * 
     */
    @TableField(value = "return_value")
    private Object returnValue;

    /**
     * 
     */
    @TableField(value = "operation")
    private String operation;

    /**
     * 
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    /**
     * 
     */
    @TableField(value = "end_time")
    private LocalDateTime endTime;

    /**
     * 
     */
    @TableField(value = "duration_ms")
    private Integer durationMs;

    /**
     * 
     */
    @TableField(value = "tags")
    private Object tags;

    /**
     * 
     */
    @TableField(value = "logs")
    private Object logs;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}