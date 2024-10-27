package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 会话表
 * @TableName tb_talk_communication
 */
@TableName(value ="tb_talk_communication")
@Data
public class TalkCommunication implements Serializable {
    /**
     * 会话表id
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
     * 逻辑删除：用来做会话的清除：0.未删除，1.删除
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
     * 消息属主
     */
    @TableField(value = "belong_id")
    private Long belongId;

    /**
     * 参与者id
     */
    @TableField(value = "participant_id")
    private Long participantId;

    /**
     * 回话id
     */
    @TableField(value = "session_id")
    private String sessionId;

    /**
     * 发送者id
     */
    @TableField(value = "sender_id")
    private Long senderId;

    /**
     * 接收者id
     */
    @TableField(value = "receiver_id")
    private Long receiverId;

    /**
     * 群聊id,用于扩展群内@消息
     */
    @TableField(value = "group_id")
    private Long groupId;

    /**
     * 最后一条消息id
     */
    @TableField(value = "last_message_id")
    private Long lastMessageId;

    /**
     * 最后一条消息的内容
     */
    @TableField(value = "last_message_content")
    private String lastMessageContent;

    /**
     * 最后确认消息id
     */
    @TableField(value = "last_ack_message")
    private Long lastAckMessage;

    /**
     * 最后的消息发送者名称
     */
    @TableField(value = "last_sender_username")
    private String lastSenderUsername;

    /**
     * 最后消息发送时间
     */
    @TableField(value = "last_send_time")
    private LocalDateTime lastSendTime;

    /**
     * 回话类型(1.个人聊天，2.群聊消息，3.系统消息,4.控制消息)
     */
    @TableField(value = "conversation_type")
    private Integer conversationType;

    /**
     * 会话修改命令（预留）
     */
    @TableField(value = "conversation_status")
    private Integer conversationStatus;

    /**
     * 会话名称
     */
    @TableField(value = "conversation_name")
    private String conversationName;

    /**
     * 未读消息数量
     */
    @TableField(value = "unread_count")
    private Integer unreadCount;

    /**
     * 草稿消息
     */
    @TableField(value = "draft")
    private String draft;

    /**
     * 是否置顶
     */
    @TableField(value = "top")
    private Boolean top;

    /**
     * 是否机器人
     */
    @TableField(value = "robot")
    private Boolean robot;

    /**
     * 是否消息免打扰
     */
    @TableField(value = "disturb")
    private Boolean disturb;

    /**
     * 是否在线
     */
    @TableField(value = "online")
    private Boolean online;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}