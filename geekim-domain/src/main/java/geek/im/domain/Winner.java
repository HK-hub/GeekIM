package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 获奖者表
 * @TableName tb_winner
 */
@TableName(value ="tb_winner")
@Data
public class Winner implements Serializable {
    /**
     * 获奖映射表
     */
    @TableId(value = "id")
    private Integer id;

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
     * 参与者获奖者id
     */
    @TableField(value = "participant_id")
    private Long participantId;

    /**
     * 奖品名称
     */
    @TableField(value = "prize_name")
    private String prizeName;

    /**
     * 奖品id
     */
    @TableField(value = "prize_id")
    private Long prizeId;

    /**
     * 是否手气王
     */
    @TableField(value = "king")
    private Boolean king;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}