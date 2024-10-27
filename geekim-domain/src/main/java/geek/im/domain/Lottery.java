package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 抽奖表
 * @TableName tb_lottery
 */
@TableName(value ="tb_lottery")
@Data
public class Lottery implements Serializable {
    /**
     * 抽奖id
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
     * 对应的消息id
     */
    @TableField(value = "message_id")
    private Long messageId;

    /**
     * 创建抽奖的用户id
     */
    @TableField(value = "creator_id")
    private Long creatorId;

    /**
     * 关联活动id
     */
    @TableField(value = "activity_id")
    private Long activityId;

    /**
     * 抽奖名称
     */
    @TableField(value = "lottery_name")
    private String lotteryName;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 抽奖开始时间
     */
    @TableField(value = "start_date")
    private LocalDateTime startDate;

    /**
     * 抽奖结束时间
     */
    @TableField(value = "end_date")
    private LocalDateTime endDate;

    /**
     * 抽奖规则：允许抽奖的用户名单
     */
    @TableField(value = "rule_list")
    private Long ruleList;

    /**
     * 奖品列表
     */
    @TableField(value = "prize_list")
    private Long prizeList;

    /**
     * 中奖率
     */
    @TableField(value = "rate")
    private Integer rate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}