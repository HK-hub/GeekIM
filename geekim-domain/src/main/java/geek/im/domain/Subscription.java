package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 订阅号
 * @TableName tb_subscription
 */
@TableName(value ="tb_subscription")
@Data
public class Subscription implements Serializable {
    /**
     * 订阅号id
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
     * 订阅号名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 订阅描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 订阅号代码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 订阅号二维码
     */
    @TableField(value = "qrcode")
    private String qrcode;

    /**
     * 订阅号类型
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 订阅号主人
     */
    @TableField(value = "master")
    private Long master;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}