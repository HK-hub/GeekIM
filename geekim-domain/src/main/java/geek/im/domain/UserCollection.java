package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用户收藏表
 * @TableName tb_user_collection
 */
@TableName(value ="tb_user_collection")
@Data
public class UserCollection implements Serializable {
    /**
     * 收藏映射id
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
     * 收藏品id
     */
    @TableField(value = "collectible_id")
    private Long collectibleId;

    /**
     * 收藏品类型
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 从哪里收藏而来
     */
    @TableField(value = "from")
    private Integer from;

    /**
     * 跳转链接
     */
    @TableField(value = "link")
    private String link;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}