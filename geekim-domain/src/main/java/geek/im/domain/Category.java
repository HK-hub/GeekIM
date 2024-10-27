package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 分类表
 * @TableName tb_category
 */
@TableName(value ="tb_category")
@Data
public class Category implements Serializable {
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
     * 分类属主用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 分类类型
     */
    @TableField(value = "category_type")
    private Integer categoryType;

    /**
     * 分类名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 分类封面
     */
    @TableField(value = "cover")
    private String cover;

    /**
     * 分类类型：1.笔记，2.说说，3.文章
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 排序顺序：越低排名越靠前
     */
    @TableField(value = "order")
    private Integer order;

    /**
     * 分类描述
     */
    @TableField(value = "description")
    private String description;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}