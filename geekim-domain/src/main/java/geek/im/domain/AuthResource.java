package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName tb_auth_resource
 */
@TableName(value ="tb_auth_resource")
@Data
public class AuthResource implements Serializable {
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
     * 
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 
     */
    @TableField(value = "key")
    private String key;

    /**
     * 
     */
    @TableField(value = "description")
    private String description;

    /**
     * 
     */
    @TableField(value = "name")
    private String name;

    /**
     * 
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 
     */
    @TableField(value = "path")
    private String path;

    /**
     * 
     */
    @TableField(value = "request_type")
    private String requestType;

    /**
     * 
     */
    @TableField(value = "controller_class")
    private String controllerClass;

    /**
     * 
     */
    @TableField(value = "controller_name")
    private String controllerName;

    /**
     * 
     */
    @TableField(value = "request_method")
    private String requestMethod;

    /**
     * 
     */
    @TableField(value = "organization_id")
    private Long organizationId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}