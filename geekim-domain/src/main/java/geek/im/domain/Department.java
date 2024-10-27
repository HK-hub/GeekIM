package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 部门表
 * @TableName tb_department
 */
@TableName(value ="tb_department")
@Data
public class Department implements Serializable {
    /**
     * 部门id
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
     * 公司id
     */
    @TableField(value = "company_id")
    private Long companyId;

    /**
     * 组织id
     */
    @TableField(value = "organize_id")
    private Long organizeId;

    /**
     * 父部门id
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 祖级部门
     */
    @TableField(value = "ancestor")
    private String ancestor;

    /**
     * 部门名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 部门排序字段：越低越排序越靠前
     */
    @TableField(value = "order")
    private Integer order;

    /**
     * 领导人名称
     */
    @TableField(value = "leader_name")
    private String leaderName;

    /**
     * 领导人id
     */
    @TableField(value = "leader_id")
    private Long leaderId;

    /**
     * 联系人电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 联系人邮箱
     */
    @TableField(value = "email")
    private String email;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}