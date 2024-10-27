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
 * @TableName tb_black_list
 */
@TableName(value ="tb_black_list")
@Data
public class BlackList implements Serializable {
    /**
     * 黑名单id
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
     * 谁的黑名单列表：用户，系统，应用，机器人，公众号，订阅号，群聊，好友等
     */
    @TableField(value = "belong_id")
    private Integer belongId;

    /**
     * 黑名单人员
     */
    @TableField(value = "blocked_id")
    private Integer blockedId;

    /**
     * 黑名单IP
     */
    @TableField(value = "blocked_ip")
    private String blockedIp;

    /**
     * 进入黑名单日期
     */
    @TableField(value = "block_date")
    private LocalDateTime blockDate;

    /**
     * 1.用户，2.群聊，3.机器人，4.公众号，5.订阅号，6.应用,7.系统
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 进入黑名单原因
     */
    @TableField(value = "reason")
    private String reason;

    /**
     * 解除黑名单日期
     */
    @TableField(value = "unblock_date")
    private LocalDateTime unblockDate;

    /**
     * 公司id,如果群聊是企业群，会进行关联
     */
    @TableField(value = "company_id")
    private Long companyId;

    /**
     * 黑名单状态
     */
    @TableField(value = "block_status")
    private Boolean blockStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}