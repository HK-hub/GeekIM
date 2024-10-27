package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 模板表
 * @TableName tb_table_template
 */
@TableName(value ="tb_table_template")
@Data
public class TableTemplate implements Serializable {
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 更新者，关联用户id
     */
    @TableField(value = "update_by")
    private Long updateBy;

    /**
     * 逻辑删除：默认false:未删除，true:删除
     */
    @TableField(value = "deleted")
    private Boolean deleted;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 是否启用
     */
    @TableField(value = "enable")
    private Boolean enable;

    /**
     * 扩展信息
     */
    @TableField(value = "extra")
    private Object extra;

    /**
     * 属性
     */
    @TableField(value = "attribute")
    private Object attribute;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}