package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用户空间表
 * @TableName tb_user_zone
 */
@TableName(value ="tb_user_zone")
@Data
public class UserZone implements Serializable {
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
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 笔记id
     */
    @TableField(value = "post_id")
    private Long postId;

    /**
     * 是否转发
     */
    @TableField(value = "forward")
    private Boolean forward;

    /**
     * 转发自那个说说
     */
    @TableField(value = "forward_from")
    private Long forwardFrom;

    /**
     * 是否草稿
     */
    @TableField(value = "draft")
    private Boolean draft;

    /**
     * 是否撤回
     */
    @TableField(value = "revoke")
    private Boolean revoke;

    /**
     * 计划发布时间
     */
    @TableField(value = "plan_time")
    private LocalDateTime planTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}