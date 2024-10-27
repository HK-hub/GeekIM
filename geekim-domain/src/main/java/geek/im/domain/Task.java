package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 系统任务表
 * @TableName tb_task
 */
@TableName(value ="tb_task")
@Data
public class Task implements Serializable {
    /**
     * 任务id
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
     * 任务触发条件
     */
    @TableField(value = "trigger_conditions")
    private String triggerConditions;

    /**
     * 任务执行者
     */
    @TableField(value = "task_executor")
    private Long taskExecutor;

    /**
     * 任务参数
     */
    @TableField(value = "task_parameters")
    private Object taskParameters;

    /**
     * 任务执行状态：1.创建中，2.已创建，3.已发布，4.待执行，5.执行中，6.执行结束，7.执行成功，8.执行失败，9.取消任务
     */
    @TableField(value = "task_status")
    private Integer taskStatus;

    /**
     * 任务优先级：数值越小优先级越高
     */
    @TableField(value = "task_priority")
    private Integer taskPriority;

    /**
     * 任务执行周期：corn表达式
     */
    @TableField(value = "task_schedule")
    private String taskSchedule;

    /**
     * 关联用户id
     */
    @TableField(value = "related_user_id")
    private Long relatedUserId;

    /**
     * 关联消息id
     */
    @TableField(value = "message_id")
    private Long messageId;

    /**
     * 任务产生消息
     */
    @TableField(value = "message_recipient")
    private String messageRecipient;

    /**
     * 任务执行产生内容
     */
    @TableField(value = "message_content")
    private String messageContent;

    /**
     * 任务通知类型
     */
    @TableField(value = "notification_type")
    private String notificationType;

    /**
     * 任务通知内容
     */
    @TableField(value = "notification_content")
    private String notificationContent;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}