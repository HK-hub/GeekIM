package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 订阅消息：订阅号，公众号，机器人等
 * @TableName tb_message_subscribe
 */
@TableName(value ="tb_message_subscribe")
@Data
public class MessageSubscribe implements Serializable {
    /**
     * 订阅消息id
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
     * 消息id
     */
    @TableField(value = "message_id")
    private Long messageId;

    /**
     * 订阅消息标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 订阅消息内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 订阅消息链接
     */
    @TableField(value = "link")
    private String link;

    /**
     * 封面
     */
    @TableField(value = "cover")
    private String cover;

    /**
     * 订阅消息类型：1.订阅号，2.公众号，3.机器人等
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 公众号id
     */
    @TableField(value = "account_id")
    private Long accountId;

    /**
     * 公众号文章id
     */
    @TableField(value = "article_id")
    private Long articleId;

    /**
     * 任务id
     */
    @TableField(value = "task_id")
    private Long taskId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}