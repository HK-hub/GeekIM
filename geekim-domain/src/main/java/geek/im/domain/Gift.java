package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 礼物表
 * @TableName tb_gift
 */
@TableName(value ="tb_gift")
@Data
public class Gift implements Serializable {
    /**
     * 礼物id
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
     * 礼物名称
     */
    @TableField(value = "gift_name")
    private String giftName;

    /**
     * 封面，图标
     */
    @TableField(value = "cover")
    private String cover;

    /**
     * 礼物价值：单位毛
     */
    @TableField(value = "gift_value")
    private Integer giftValue;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}