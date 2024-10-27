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
 * @TableName tb_employee_position
 */
@TableName(value ="tb_employee_position")
@Data
public class EmployeePosition implements Serializable {
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
     * 员工id
     */
    @TableField(value = "employee_id")
    private Long employeeId;

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
     * 职位id
     */
    @TableField(value = "position_id")
    private Long positionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}