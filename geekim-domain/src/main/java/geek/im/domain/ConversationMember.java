package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 通话成员表
 * @TableName tb_conversation_member
 */
@TableName(value ="tb_conversation_member")
@Data
public class ConversationMember implements Serializable {
    /**
     * 成员id
     */
    @TableId(value = "member_id")
    private Long memberId;

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
    @TableField(value = "conversation_id")
    private Long conversationId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 群组id
     */
    @TableField(value = "group_id")
    private Long groupId;

    /**
     * 通话成员加入时间
     */
    @TableField(value = "join_time")
    private LocalDateTime joinTime;

    /**
     * 离开时间
     */
    @TableField(value = "left_time")
    private LocalDateTime leftTime;

    /**
     * 成员角色
     */
    @TableField(value = "member_role")
    private Integer memberRole;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}