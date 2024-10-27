package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用户主题表
 * @TableName tb_user_theme
 */
@TableName(value ="tb_user_theme")
@Data
public class UserTheme implements Serializable {
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
     * 黑暗模式
     */
    @TableField(value = "dark_mode")
    private Boolean darkMode;

    /**
     * 主题模板
     */
    @TableField(value = "theme_template")
    private Integer themeTemplate;

    /**
     * 主题颜色
     */
    @TableField(value = "theme_color")
    private String themeColor;

    /**
     * 消息铃声
     */
    @TableField(value = "message_tone")
    private Integer messageTone;

    /**
     * 系统提示
     */
    @TableField(value = "system_notify")
    private Boolean systemNotify;

    /**
     * 主题模式
     */
    @TableField(value = "theme_mode")
    private Integer themeMode;

    /**
     * 个人卡片背景
     */
    @TableField(value = "card_background")
    private String cardBackground;

    /**
     * 主题背景
     */
    @TableField(value = "theme_background")
    private String themeBackground;

    /**
     * 聊天背景
     */
    @TableField(value = "talk_background")
    private String talkBackground;

    /**
     * 音视频通话铃声
     */
    @TableField(value = "ring_tone")
    private String ringTone;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}