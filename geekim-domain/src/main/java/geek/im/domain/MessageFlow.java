package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 消息流水表
 * @TableName tb_message_flow
 */
@TableName(value ="tb_message_flow")
@Data
public class MessageFlow implements Serializable {
    /**
     * 消息流水id
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
     * 群聊id
     */
    @TableField(value = "group_id")
    private Long groupId;

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
     * 音视频通话id
     */
    @TableField(value = "conversation_id")
    private Long conversationId;

    /**
     * 回复消息id
     */
    @TableField(value = "replied_id")
    private Long repliedId;

    /**
     * 会话类型:1.个人聊天,2.群聊，3.系统消息,
     */
    @TableField(value = "talk_type")
    private Integer talkType;

    /**
     * 消息类型:1.文本，2.图片，3.语音，4.图文混合，5.文件，6.语音通话，7.视频通话，
                                8.白板演示，9.远程控制，10.日程安排，11.外部分享,12.@消息
     */
    @TableField(value = "message_type")
    private Integer messageType;

    /**
     * 消息id
     */
    @TableField(value = "message_id")
    private Long messageId;

    /**
     * 消息序列号，发号器分配的id 号
     */
    @TableField(value = "sequence")
    private Long sequence;

    /**
     * 消息发送状态：1.发送中，2.已发送，3.发送失败,4.草稿，
     */
    @TableField(value = "send_status")
    private Integer sendStatus;

    /**
     * 签收状态：1.未读，2.已读，3.忽略，4.撤回，5.删除
     */
    @TableField(value = "sign_flag")
    private Integer signFlag;

    /**
     * 是否撤回
     */
    @TableField(value = "revoke")
    private Boolean revoke;

    /**
     * 是否送达
     */
    @TableField(value = "delivered")
    private Boolean delivered;

    /**
     * 
     */
    @TableField(value = "receive_time")
    private LocalDateTime receiveTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}