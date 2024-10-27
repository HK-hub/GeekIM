package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 小程序表
 * @TableName tb_mini_app
 */
@TableName(value ="tb_mini_app")
@Data
public class MiniApp implements Serializable {
    /**
     * 小程序id
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
     * 小程序key
     */
    @TableField(value = "app_key")
    private String appKey;

    /**
     * 小程序open_id
     */
    @TableField(value = "open_id")
    private String openId;

    /**
     * 安全密钥
     */
    @TableField(value = "secrete_key")
    private String secreteKey;

    /**
     * api 访问密钥
     */
    @TableField(value = "access_key")
    private String accessKey;

    /**
     * 小程序名称
     */
    @TableField(value = "app_name")
    private String appName;

    /**
     * 小程序描述
     */
    @TableField(value = "app_description")
    private String appDescription;

    /**
     * 小程序拥有者
     */
    @TableField(value = "owner_id")
    private Long ownerId;

    /**
     * 小程序开发者
     */
    @TableField(value = "developer_name")
    private String developerName;

    /**
     * 开发商描述
     */
    @TableField(value = "developer_description")
    private String developerDescription;

    /**
     * 开发商联系邮箱
     */
    @TableField(value = "contact_email")
    private String contactEmail;

    /**
     * 小程序链接
     */
    @TableField(value = "link")
    private String link;

    /**
     * 小程序版本
     */
    @TableField(value = "version")
    private String version;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}