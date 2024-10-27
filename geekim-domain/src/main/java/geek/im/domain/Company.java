package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 公司表
 * @TableName tb_company
 */
@TableName(value ="tb_company")
@Data
public class Company implements Serializable {
    /**
     * 公司id
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
     * 母公司id
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 组织结构id
     */
    @TableField(value = "organize_id")
    private Long organizeId;

    /**
     * 公司名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 公司描述
     */
    @TableField(value = "description")
    private String description;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}