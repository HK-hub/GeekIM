package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 群成员表
 * @TableName tb_group_member
 */
@TableName(value ="tb_group_member")
@Data
public class GroupMember implements Serializable {
    /**
     * 群成员id
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
     * 群id
     */
    @TableField(value = "group_id")
    private Long groupId;

    /**
     * 群号
     */
    @TableField(value = "group_account")
    private Long groupAccount;

    /**
     * 群成员id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 群成员群外昵称
     */
    @TableField(value = "username")
    private String username;

    /**
     * 群成员的群内昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 群成员头像(缩略图)
     */
    @TableField(value = "member_avatar")
    private String memberAvatar;

    /**
     * 群员角色:1.普通成员，2.管理员，3.群主
     */
    @TableField(value = "member_role")
    private Integer memberRole;

    /**
     * 是否管理员
     */
    @TableField(value = "admin")
    private Boolean admin;

    /**
     * 
     */
    @TableField(value = "master")
    private Boolean master;

    /**
     * 管理员权限列表
     */
    @TableField(value = "permission_list")
    private String permissionList;

    /**
     * 群分租
     */
    @TableField(value = "group_category")
    private String groupCategory;

    /**
     * 禁言时间：表示禁止发言的结束时间
     */
    @TableField(value = "gag_time")
    private LocalDateTime gagTime;

    /**
     * 最后确认的消息id
     */
    @TableField(value = "last_ack_message")
    private Long lastAckMessage;

    /**
     * 最后查看消息的时间
     */
    @TableField(value = "last_seen")
    private LocalDateTime lastSeen;

    /**
     * 是否被永久禁言
     */
    @TableField(value = "muted")
    private Boolean muted;

    /**
     * 加入群聊时间
     */
    @TableField(value = "join_time")
    private LocalDateTime joinTime;

    /**
     * 群聊消息免打扰
     */
    @TableField(value = "disturb")
    private Boolean disturb;

    /**
     * 群组在群成员联系人列表中的群分组id
     */
    @TableField(value = "category_id")
    private Long categoryId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}