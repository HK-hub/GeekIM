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
 * @TableName tb_group_apply
 */
@TableName(value ="tb_group_apply")
@Data
public class GroupApply implements Serializable {
    /**
     * 群聊申请单id
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
     * 申请者id
     */
    @TableField(value = "applicant_id")
    private Long applicantId;

    /**
     * 群id
     */
    @TableField(value = "group_id")
    private Long groupId;

    /**
     * 处理者id
     */
    @TableField(value = "handler_id")
    private Long handlerId;

    /**
     * 申请类型：1.搜索加群，2.邀请入群，3.扫码加群
     */
    @TableField(value = "apply_type")
    private Integer applyType;

    /**
     * 申请说明信息,验证信息
     */
    @TableField(value = "apply_info")
    private String applyInfo;

    /**
     * 申请问题答案
     */
    @TableField(value = "apply_answer")
    private String applyAnswer;

    /**
     * 群聊申请通过之后对群聊的备注名
     */
    @TableField(value = "group_remark_name")
    private String groupRemarkName;

    /**
     * 进群之后群内昵称
     */
    @TableField(value = "user_nickname")
    private String userNickname;

    /**
     * 申请通过之后群聊的分组id
     */
    @TableField(value = "apply_grouping_id")
    private Long applyGroupingId;

    /**
     * 处理结果信息
     */
    @TableField(value = "handle_info")
    private String handleInfo;

    /**
     * 是否签收
     */
    @TableField(value = "sign")
    private Integer sign;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}