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
 * @TableName tb_group_setting
 */
@TableName(value ="tb_group_setting")
@Data
public class GroupSetting implements Serializable {
    /**
     * 群id
     */
    @TableId(value = "group_id")
    private Long groupId;

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
     * 群号
     */
    @TableField(value = "group_account")
    private Long groupAccount;

    /**
     * 群定位:国家-省份-城市-区-县-镇
     */
    @TableField(value = "position")
    private String position;

    /**
     * 群人数限制:200人，500人，1000人，2000人
     */
    @TableField(value = "member_capacity")
    private Integer memberCapacity;

    /**
     * 是否私人群聊
     */
    @TableField(value = "private_group")
    private Boolean privateGroup;

    /**
     * 是否企业群聊
     */
    @TableField(value = "company_group")
    private Boolean companyGroup;

    /**
     * 如果是企业群聊，对应的企业id
     */
    @TableField(value = "company_id")
    private Long companyId;

    /**
     * 
     */
    @TableField(value = "group_type")
    private Integer groupType;

    /**
     * 发现群方式：1.公开群(支持搜索群名称，群号，群二维码，邀请)，2.不公开群(不支持搜索群名称，支持搜索群号，群二维码，邀请)，3.邀请制(只能通过成员邀请)
     */
    @TableField(value = "find_type")
    private Integer findType;

    /**
     * 加群方式：1.允许任何人everybody,2.需要验证verification, 3.不允许人加群nobody, 4.回答问题'
     */
    @TableField(value = "join_type")
    private Integer joinType;

    /**
     * 是否允许加入
     */
    @TableField(value = "allow_join")
    private Boolean allowJoin;

    /**
     * 新人入群欢迎消息
     */
    @TableField(value = "welcome_message")
    private String welcomeMessage;

    /**
     * 
     */
    @TableField(value = "group_rules")
    private String groupRules;

    /**
     * 加入群聊问题
     */
    @TableField(value = "join_problem")
    private String joinProblem;

    /**
     * 加入群聊答案
     */
    @TableField(value = "answer")
    private String answer;

    /**
     * 全员禁言
     */
    @TableField(value = "ban_speak")
    private Integer banSpeak;

    /**
     * 
     */
    @TableField(value = "enable_temporary")
    private Boolean enableTemporary;

    /**
     * 最新的群公告
     */
    @TableField(value = "announcement")
    private String announcement;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}