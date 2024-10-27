package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 好友申请表
 * @TableName tb_friend_apply
 */
@TableName(value ="tb_friend_apply")
@Data
public class FriendApply implements Serializable {
    /**
     * id编号
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
     * 申请发起人
     */
    @TableField(value = "sender_id")
    private Long senderId;

    /**
     * 申请接收人,如果是加群则为群号
     */
    @TableField(value = "receiver_id")
    private Long receiverId;

    /**
     * 申请类型：1.好友申请，2.加群申请
     */
    @TableField(value = "apply_type")
    private Integer applyType;

    /**
     * 好友申请说明信息,验证信息
     */
    @TableField(value = "apply_info")
    private String applyInfo;

    /**
     * 好友申请回答
     */
    @TableField(value = "apply_answer")
    private String applyAnswer;

    /**
     * 好友申请通过之后对好友的备注名
     */
    @TableField(value = "remark_name")
    private String remarkName;

    /**
     * 好友申请通过之后好友的分组id
     */
    @TableField(value = "apply_grouping_id")
    private Long applyGroupingId;

    /**
     * 处理信息
     */
    @TableField(value = "handle_info")
    private String handleInfo;

    /**
     * 处理结果
     */
    @TableField(value = "handle_result")
    private Integer handleResult;

    /**
     * 签收状态：0.未签收，1.已签收
     */
    @TableField(value = "sign")
    private Integer sign;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}