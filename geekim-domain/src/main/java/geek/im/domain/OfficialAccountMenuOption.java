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
 * @TableName tb_official_account_menu_option
 */
@TableName(value ="tb_official_account_menu_option")
@Data
public class OfficialAccountMenuOption implements Serializable {
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
    @TableField(value = "name")
    private String name;

    /**
     * 
     */
    @TableField(value = "menu_id")
    private Long menuId;

    /**
     * 
     */
    @TableField(value = "account_id")
    private Long accountId;

    /**
     * 
     */
    @TableField(value = "link")
    private String link;

    /**
     * 
     */
    @TableField(value = "option_type")
    private Integer optionType;

    /**
     * 
     */
    @TableField(value = "order")
    private Integer order;

    /**
     * 
     */
    @TableField(value = "children_list")
    private Long childrenList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}