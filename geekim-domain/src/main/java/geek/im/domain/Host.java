package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 主播表
 * @TableName tb_host
 */
@TableName(value ="tb_host")
@Data
public class Host implements Serializable {
    /**
     * 主播id
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
     * 关联用户
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 主播用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 主播用户名
     */
    @TableField(value = "display_name")
    private String displayName;

    /**
     * 直播内容主题
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 主播个人封面
     */
    @TableField(value = "cover")
    private String cover;

    /**
     * 主播简介
     */
    @TableField(value = "resume")
    private String resume;

    /**
     * 粉丝数
     */
    @TableField(value = "follower_count")
    private Integer followerCount;

    /**
     * 主播关注数
     */
    @TableField(value = "following_count")
    private Integer followingCount;

    /**
     * 性别
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    private LocalDate birthday;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}