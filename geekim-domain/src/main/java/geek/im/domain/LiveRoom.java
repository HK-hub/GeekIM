package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 直播间
 * @TableName tb_live_room
 */
@TableName(value ="tb_live_room")
@Data
public class LiveRoom implements Serializable {
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
     * 主播id
     */
    @TableField(value = "host_id")
    private Long hostId;

    /**
     * 媒体流key
     */
    @TableField(value = "stream_key")
    private String streamKey;

    /**
     * 直播间名称
     */
    @TableField(value = "room_name")
    private String roomName;

    /**
     * 直播间标题
     */
    @TableField(value = "room_title")
    private String roomTitle;

    /**
     * 直播内容类型
     */
    @TableField(value = "live_type")
    private Integer liveType;

    /**
     * 直播间描述
     */
    @TableField(value = "room_description")
    private String roomDescription;

    /**
     * 直播开始时间
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    /**
     * 直播间结束时间
     */
    @TableField(value = "end_time")
    private LocalDateTime endTime;

    /**
     * 直播间观众数量
     */
    @TableField(value = "audience_count")
    private Integer audienceCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}