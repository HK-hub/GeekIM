package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用户设置表
 * @TableName tb_user_setting
 */
@TableName(value ="tb_user_setting")
@Data
public class UserSetting implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "user_id")
    private Long userId;

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
     * 隐私模式：开启之后别人无法发现你
     */
    @TableField(value = "privacy")
    private Boolean privacy;

    /**
     * 加好友策略：0.直接同意，1.验证，2.回答问题，3.输入密码
     */
    @TableField(value = "add_friend_policy")
    private Integer addFriendPolicy;

    /**
     * 加好友问题
     */
    @TableField(value = "add_friend_question")
    private String addFriendQuestion;

    /**
     * 加好友答案，密码
     */
    @TableField(value = "add_friend_answer")
    private String addFriendAnswer;

    /**
     * 存储空间：KB, 默认5G
     */
    @TableField(value = "storage")
    private Long storage;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}