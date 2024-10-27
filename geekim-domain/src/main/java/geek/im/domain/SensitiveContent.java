package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 敏感内容表
 * @TableName tb_sensitive_content
 */
@TableName(value ="tb_sensitive_content")
@Data
public class SensitiveContent implements Serializable {
    /**
     * 主键ID
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
     * 敏感词
     */
    @TableField(value = "word")
    private String word;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 类型
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 模块
     */
    @TableField(value = "module")
    private String module;

    /**
     * 等级
     */
    @TableField(value = "level")
    private Integer level;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}