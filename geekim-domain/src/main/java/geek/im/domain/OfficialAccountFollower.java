package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 公众号-关注者映射表
 * @TableName tb_official_account_follower
 */
@TableName(value ="tb_official_account_follower")
@Data
public class OfficialAccountFollower implements Serializable {
    /**
     * 映射id
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
     * 关注者用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 公众号id
     */
    @TableField(value = "account_id")
    private Long accountId;

    /**
     * 关注日期
     */
    @TableField(value = "follow_date")
    private LocalDateTime followDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}