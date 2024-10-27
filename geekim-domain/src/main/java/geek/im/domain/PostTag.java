package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 说说-标签映射表
 * @TableName tb_post_tag
 */
@TableName(value ="tb_post_tag")
@Data
public class PostTag implements Serializable {
    /**
     * 说说-标签映射id
     */
    @TableId(value = "id")
    private Integer id;

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
     * 说说id
     */
    @TableField(value = "post_id")
    private Long postId;

    /**
     * 标签列表
     */
    @TableField(value = "tag_id_list")
    private Long tagIdList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}