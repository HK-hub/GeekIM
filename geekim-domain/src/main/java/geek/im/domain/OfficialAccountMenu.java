package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 公众号菜单
 * @TableName tb_official_account_menu
 */
@TableName(value ="tb_official_account_menu")
@Data
public class OfficialAccountMenu implements Serializable {
    /**
     * 公众号菜单id
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
     * 菜单名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 公众号
     */
    @TableField(value = "account_id")
    private Long accountId;

    /**
     * 父级菜单id
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 跳转链接：1.小程序跳转，2.外部链接跳转：inner(mini_app_key:page_link)
     */
    @TableField(value = "jump_link")
    private String jumpLink;

    /**
     * 跳转方式：1.小程序跳转，2.外部跳转
     */
    @TableField(value = "jump_type")
    private Integer jumpType;

    /**
     * 菜单类型：1文本，2.链接等
     */
    @TableField(value = "menu_type")
    private Integer menuType;

    /**
     * 菜单排序顺序，顺序越小排序越靠前
     */
    @TableField(value = "order")
    private Integer order;

    /**
     * 子菜单id 
     */
    @TableField(value = "children_id_list")
    private Long childrenIdList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}