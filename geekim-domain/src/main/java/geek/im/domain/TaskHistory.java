package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 任务历史表
 * @TableName tb_task_history
 */
@TableName(value ="tb_task_history")
@Data
public class TaskHistory implements Serializable {
    /**
     * 任务历史id
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
     * 机器人id
     */
    @TableField(value = "robot_id")
    private Long robotId;

    /**
     * 任务id
     */
    @TableField(value = "task_id")
    private Long taskId;

    /**
     * 任务名称
     */
    @TableField(value = "task_name")
    private String taskName;

    /**
     * 任务类型
     */
    @TableField(value = "task_type")
    private Integer taskType;

    /**
     * 执行时间
     */
    @TableField(value = "execution_time")
    private LocalDateTime executionTime;

    /**
     * 执行时长
     */
    @TableField(value = "execution_duration")
    private Integer executionDuration;

    /**
     * 执行完成时间
     */
    @TableField(value = "completion_time")
    private LocalDateTime completionTime;

    /**
     * 任务状态
     */
    @TableField(value = "task_status")
    private Integer taskStatus;

    /**
     * 任务结果
     */
    @TableField(value = "task_result")
    private Object taskResult;

    /**
     * 任务参数
     */
    @TableField(value = "parameters")
    private Object parameters;

    /**
     * 响应数据
     */
    @TableField(value = "response_data")
    private Object responseData;

    /**
     * 执行者id
     */
    @TableField(value = "executor_id")
    private Long executorId;

    /**
     * 触发条件
     */
    @TableField(value = "trigger_condition")
    private String triggerCondition;

    /**
     * 执行日志
     */
    @TableField(value = "execution_log")
    private String executionLog;

    /**
     * 关联用户id
     */
    @TableField(value = "related_user_id")
    private Long relatedUserId;

    /**
     * 链路追踪id
     */
    @TableField(value = "trace_id")
    private String traceId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}