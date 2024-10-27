package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用户信息表
 * @TableName tb_user_info
 */
@TableName(value ="tb_user_info")
@Data
public class UserInfo implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "user_id")
    private Long userId;

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
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * QQ号
     */
    @TableField(value = "qq")
    private String qq;

    /**
     * 微信号
     */
    @TableField(value = "wechat")
    private String wechat;

    /**
     * 
     */
    @TableField(value = "github")
    private String github;

    /**
     * 
     */
    @TableField(value = "dingtalk")
    private String dingtalk;

    /**
     * 最后交互时间
     */
    @TableField(value = "last_time")
    private LocalDateTime lastTime;

    /**
     * 性别
     */
    @TableField(value = "gender")
    private Boolean gender;

    /**
     * 年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    private LocalDate birthday;

    /**
     * 星座
     */
    @TableField(value = "constellation")
    private String constellation;

    /**
     * 学校
     */
    @TableField(value = "campus")
    private String campus;

    /**
     * 专业,主修
     */
    @TableField(value = "major")
    private String major;

    /**
     * 职业
     */
    @TableField(value = "job")
    private String job;

    /**
     * 所在国家
     */
    @TableField(value = "country")
    private String country;

    /**
     * 所在城市
     */
    @TableField(value = "city")
    private String city;

    /**
     * 兴趣爱好
     */
    @TableField(value = "interest")
    private String interest;

    /**
     * 个人标签,不超过6个标签，每个标签不超过6个字
     */
    @TableField(value = "tag")
    private String tag;

    /**
     * 个性签名，类比QQ签名
     */
    @TableField(value = "motto")
    private String motto;

    /**
     * 用户个人名片背景
     */
    @TableField(value = "background")
    private String background;

    /**
     * 个人标签id集合
     */
    @TableField(value = "tag_id_list")
    private Long tagIdList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}