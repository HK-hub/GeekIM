package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 直播观众表
 * @TableName tb_live_audience
 */
@TableName(value ="tb_live_audience")
@Data
public class LiveAudience implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 直播间id
     */
    @TableField(value = "room_id")
    private Long roomId;

    /**
     * 进入直播间时间
     */
    @TableField(value = "join_time")
    private LocalDateTime joinTime;

    /**
     * 观众在直播间牌子
     */
    @TableField(value = "tag")
    private String tag;

    /**
     * 是否关注主播
     */
    @TableField(value = "follow")
    private Boolean follow;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}