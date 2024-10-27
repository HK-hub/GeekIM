package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 抽奖参与者
 * @TableName tb_lottery_participant
 */
@TableName(value ="tb_lottery_participant")
@Data
public class LotteryParticipant implements Serializable {
    /**
     * 抽奖-参与者映射表id
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
     * 抽奖id
     */
    @TableField(value = "lottery_id")
    private Long lotteryId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 抽奖编码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 抽奖结果
     */
    @TableField(value = "result")
    private String result;

    /**
     * 是否获奖
     */
    @TableField(value = "winner")
    private Boolean winner;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}