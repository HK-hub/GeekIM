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
 * @TableName tb_call_participant
 */
@TableName(value ="tb_call_participant")
@Data
public class CallParticipant implements Serializable {
    /**
     * 通话参与者id
     */
    @TableId(value = "participant_id")
    private Long participantId;

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
     * 通话id
     */
    @TableField(value = "call_id")
    private Long callId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 
     */
    @TableField(value = "caller")
    private Boolean caller;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}