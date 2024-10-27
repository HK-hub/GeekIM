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
 * @TableName tb_inbox
 */
@TableName(value ="tb_inbox")
@Data
public class Inbox implements Serializable {
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
    @TableField(value = "receiver_id")
    private Long receiverId;

    /**
     * 
     */
    @TableField(value = "sender_id")
    private Long senderId;

    /**
     * 
     */
    @TableField(value = "message_id")
    private Long messageId;

    /**
     * 
     */
    @TableField(value = "conversation_id")
    private Long conversationId;

    /**
     * 
     */
    @TableField(value = "talk_type")
    private Integer talkType;

    /**
     * 
     */
    @TableField(value = "message_type")
    private Integer messageType;

    /**
     * 
     */
    @TableField(value = "content_summary")
    private String contentSummary;

    /**
     * 
     */
    @TableField(value = "message_status")
    private Integer messageStatus;

    /**
     * 
     */
    @TableField(value = "sign")
    private Boolean sign;

    /**
     * 
     */
    @TableField(value = "delete")
    private Boolean delete;

    /**
     * 
     */
    @TableField(value = "revoke")
    private Boolean revoke;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}