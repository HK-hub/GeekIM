package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 机器人任务表
 * @TableName tb_robot_task
 */
@TableName(value ="tb_robot_task")
@Data
public class RobotTask implements Serializable {
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
     * 任务id
     */
    @TableField(value = "task_id")
    private Long taskId;

    /**
     * 机器人id
     */
    @TableField(value = "robot_id")
    private Long robotId;

    /**
     * 任务名称
     */
    @TableField(value = "task_name")
    private String taskName;

    /**
     * 任务类型
     */
    @TableField(value = "task_type")
    private String taskType;

    /**
     * 触发条件
     */
    @TableField(value = "trigger_conditions")
    private String triggerConditions;

    /**
     * 任务执行者
     */
    @TableField(value = "task_executor")
    private Long taskExecutor;

    /**
     * 响应消息模板
     */
    @TableField(value = "response_message_template")
    private String responseMessageTemplate;

    /**
     * 任务参数
     */
    @TableField(value = "task_parameters")
    private Object taskParameters;

    /**
     * 任务状态：1.创建中，2.已创建，3.待执行，4.执行中，5.执行完毕，6.执行失败，7.取消
     */
    @TableField(value = "task_status")
    private Integer taskStatus;

    /**
     * 任务优先级
     */
    @TableField(value = "task_priority")
    private Integer taskPriority;

    /**
     * 任务执行周期cron 表达式
     */
    @TableField(value = "task_schedule")
    private String taskSchedule;

    /**
     * 关联用户id
     */
    @TableField(value = "related_user_id")
    private Long relatedUserId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}