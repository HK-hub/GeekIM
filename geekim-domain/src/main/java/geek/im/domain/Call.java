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
 * @TableName tb_call
 */
@TableName(value ="tb_call")
@Data
public class Call implements Serializable {
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
     * 发起音视频通话的拨号者
     */
    @TableField(value = "caller_id")
    private Long callerId;

    /**
     * 接收音视频通话邀请的用户id
     */
    @TableField(value = "receiver_id")
    private Long receiverId;

    /**
     * 通话类型：1.音频通话，2.视频通话
     */
    @TableField(value = "call_type")
    private Long callType;

    /**
     * 聊天类型：1.私聊，2.群聊，3.机器人，4.直播，5.社区
     */
    @TableField(value = "talk_type")
    private Integer talkType;

    /**
     * 通话开始时间
     */
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    /**
     * 通话结束时间
     */
    @TableField(value = "end_time")
    private LocalDateTime endTime;

    /**
     * 分享链接
     */
    @TableField(value = "share_link")
    private String shareLink;

    /**
     * 通话持续时间
     */
    @TableField(value = "duration")
    private Integer duration;

    /**
     * 是否拒绝接听
     */
    @TableField(value = "rejected")
    private Boolean rejected;

    /**
     * 通话结束原因
     */
    @TableField(value = "end_reason")
    private String endReason;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}