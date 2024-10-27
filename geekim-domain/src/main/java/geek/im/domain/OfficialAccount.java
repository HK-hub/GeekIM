package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 公众号表
 * @TableName tb_official_account
 */
@TableName(value ="tb_official_account")
@Data
public class OfficialAccount implements Serializable {
    /**
     * 公众号id
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
     * 公众号名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 公众号描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 公众号编码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 公众号二维码
     */
    @TableField(value = "qrcode")
    private String qrcode;

    /**
     * 公众号图标
     */
    @TableField(value = "logo")
    private String logo;

    /**
     * 公众号内容类型
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 公众号主人
     */
    @TableField(value = "master")
    private Long master;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}