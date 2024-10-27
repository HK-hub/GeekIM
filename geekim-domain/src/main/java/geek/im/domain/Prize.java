package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 抽奖奖品表
 * @TableName tb_prize
 */
@TableName(value ="tb_prize")
@Data
public class Prize implements Serializable {
    /**
     * 抽奖奖品id
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
     * 奖品名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 奖品描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 奖品类型
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 奖品数量
     */
    @TableField(value = "quantity")
    private Integer quantity;

    /**
     * 剩余奖品数量
     */
    @TableField(value = "remaining")
    private Integer remaining;

    /**
     * 中奖率
     */
    @TableField(value = "rate")
    private Integer rate;

    /**
     * 奖品图片集合
     */
    @TableField(value = "picture_list")
    private String pictureList;

    /**
     * 奖品价值
     */
    @TableField(value = "price")
    private Integer price;

    /**
     * 是否参与奖
     */
    @TableField(value = "participation_award")
    private Boolean participationAward;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}