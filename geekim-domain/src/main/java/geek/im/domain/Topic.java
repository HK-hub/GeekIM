package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 主题表(用于说说活动等)
 * @TableName tb_topic
 */
@TableName(value ="tb_topic")
@Data
public class Topic implements Serializable {
    /**
     * 主题表
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
     * 主题名称
     */
    @TableField(value = "topic_name")
    private String topicName;

    /**
     * 主题描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 开始时间
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @TableField(value = "end_time")
    private LocalDateTime endTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}