package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 直播间礼物打赏
 * @TableName tb_live_gift
 */
@TableName(value ="tb_live_gift")
@Data
public class LiveGift implements Serializable {
    /**
     * 直播间礼物打赏id
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
     * 直播间id
     */
    @TableField(value = "room_id")
    private Long roomId;

    /**
     * 主播id
     */
    @TableField(value = "host_id")
    private Long hostId;

    /**
     * 观众id
     */
    @TableField(value = "audience_id")
    private Long audienceId;

    /**
     * 礼物id
     */
    @TableField(value = "gift_id")
    private Long giftId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}