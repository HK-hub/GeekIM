package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 好友表
 * @TableName tb_friend
 */
@TableName(value ="tb_friend")
@Data
public class Friend implements Serializable {
    /**
     * 好友关系id
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
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 好友id
     */
    @TableField(value = "friend_id")
    private Long friendId;

    /**
     * 状态：0.陌生人(临时会话)，1.好友，2.黑名单，3.特别关心，4.删除
     */
    @TableField(value = "relation")
    private Integer relation;

    /**
     * 分组:如果不是好友，默认临时会话
     */
    @TableField(value = "group")
    private String group;

    /**
     * 分组id
     */
    @TableField(value = "group_id")
    private Long groupId;

    /**
     * 好友昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 备注姓名
     */
    @TableField(value = "remark_name")
    private String remarkName;

    /**
     * 备注信息
     */
    @TableField(value = "remark_info")
    private String remarkInfo;

    /**
     * 是否为机器人：0.否，1.是
     */
    @TableField(value = "robot")
    private Boolean robot;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 是否消息免打扰：0.否，1.是
     */
    @TableField(value = "disturb")
    private Boolean disturb;

    /**
     * 是否置顶：0.否，1.是
     */
    @TableField(value = "top")
    private Boolean top;

    /**
     * 特别关心
     */
    @TableField(value = "favorite")
    private Boolean favorite;

    /**
     * 黑名单
     */
    @TableField(value = "block")
    private Boolean block;

    /**
     * 在线状态
     */
    @TableField(value = "online")
    private Boolean online;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}