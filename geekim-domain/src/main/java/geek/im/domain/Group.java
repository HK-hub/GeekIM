package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 群聊表
 * @TableName tb_group
 */
@TableName(value ="tb_group")
@Data
public class Group implements Serializable {
    /**
     * 群id
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
     * 公司id,如果是公司群的话
     */
    @TableField(value = "company_id")
    private Long companyId;

    /**
     * 群账号
     */
    @TableField(value = "group_account")
    private Long groupAccount;

    /**
     * 群聊名称
     */
    @TableField(value = "group_name")
    private String groupName;

    /**
     * 群聊头像
     */
    @TableField(value = "group_avatar")
    private String groupAvatar;

    /**
     * 群描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 群主题类型:0.未知，1.兴趣爱好，2.行业交流，3.生活休闲，3.学习考试，4.娱乐游戏，5.置业安家，6.品牌产品，7.粉丝，8.同学同事，9.家校师生'
     */
    @TableField(value = "group_topic")
    private Integer groupTopic;

    /**
     * 群聊类型：1.普通群聊，2.企业群聊，3.临时群聊，4.面试群，5.会议群，6.恰饭群
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 群二维码
     */
    @TableField(value = "qrcode")
    private String qrcode;

    /**
     * 群人数
     */
    @TableField(value = "member_count")
    private Integer memberCount;

    /**
     * 群主
     */
    @TableField(value = "group_master")
    private Long groupMaster;

    /**
     * 解散时间：如果是临时群的话
     */
    @TableField(value = "disband_time")
    private LocalDateTime disbandTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}