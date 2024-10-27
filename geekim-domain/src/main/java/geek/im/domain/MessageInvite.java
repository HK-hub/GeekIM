package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 邀请消息
 * @TableName tb_message_invite
 */
@TableName(value ="tb_message_invite")
@Data
public class MessageInvite implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Integer id;

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
     * 加群，退群，提出群里，同意加群，邀请加群
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 操作人的用户ID(邀请人OR管理员ID)， 本人
     */
    @TableField(value = "operator_id")
    private Integer operatorId;

    /**
     * 邀请者id
     */
    @TableField(value = "inviter_id")
    private Long inviterId;

    /**
     * 群聊id
     */
    @TableField(value = "group_id")
    private Long groupId;

    /**
     * 邀请的用户id
     */
    @TableField(value = "user_id_list")
    private Long userIdList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}