package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 联系人分组表
 * @TableName tb_contact_group
 */
@TableName(value ="tb_contact_group")
@Data
public class ContactGroup implements Serializable {
    /**
     * 分组id
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
     * 分组名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 分组人数
     */
    @TableField(value = "count")
    private Integer count;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}