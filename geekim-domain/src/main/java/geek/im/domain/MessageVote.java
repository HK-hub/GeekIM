package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 投票消息表
 * @TableName tb_message_vote
 */
@TableName(value ="tb_message_vote")
@Data
public class MessageVote implements Serializable {
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
     * 消息id
     */
    @TableField(value = "message_id")
    private Long messageId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 抽奖消息标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 投票模式
     */
    @TableField(value = "answer_mode")
    private Integer answerMode;

    /**
     * 投票选项
     */
    @TableField(value = "answer_option")
    private Object answerOption;

    /**
     * 应投票人员数量
     */
    @TableField(value = "answer_num")
    private Integer answerNum;

    /**
     * 实际投票人数
     */
    @TableField(value = "answered_num")
    private Integer answeredNum;

    /**
     * 是否允许匿名投票
     */
    @TableField(value = "anonymous")
    private Boolean anonymous;

    /**
     * 投票开始时间：可能是计划时间
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    /**
     * 投票计划结束时间
     */
    @TableField(value = "end_time")
    private LocalDateTime endTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}