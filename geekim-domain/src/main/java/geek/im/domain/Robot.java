package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 机器人表
 * @TableName tb_robot
 */
@TableName(value ="tb_robot")
@Data
public class Robot implements Serializable {
    /**
     * 机器人id
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
     * 机器人名称
     */
    @TableField(value = "robot_name")
    private String robotName;

    /**
     * 机器人描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 机器人头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 允许机器人与用户交流
     */
    @TableField(value = "enable_talk")
    private Boolean enableTalk;

    /**
     * 机器人类型：1.登录机器人，2.AI机器人，3.群聊机器人，4.开发机器人，5.报警机器人
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 机器人api key
     */
    @TableField(value = "api_key")
    private String apiKey;

    /**
     * 机器人权限级别
     */
    @TableField(value = "permission_level")
    private Integer permissionLevel;

    /**
     * 机器人配置
     */
    @TableField(value = "configuration")
    private String configuration;

    /**
     * 机器人接口
     */
    @TableField(value = "interface")
    private String interface;

    /**
     * 响应消息模板
     */
    @TableField(value = "response_message_template")
    private String responseMessageTemplate;

    /**
     * 触发条件
     */
    @TableField(value = "trigger_conditions")
    private String triggerConditions;

    /**
     * 定时任务
     */
    @TableField(value = "scheduled_task")
    private String scheduledTask;

    /**
     * 数据访问权限
     */
    @TableField(value = "data_access_permission")
    private Integer dataAccessPermission;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}