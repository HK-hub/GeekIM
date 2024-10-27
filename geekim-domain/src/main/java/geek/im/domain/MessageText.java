package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 文本消息表
 * @TableName tb_message_text
 */
@TableName(value ="tb_message_text")
@Data
public class MessageText implements Serializable {
    /**
     * 主键ID
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
     * 文本内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 文本消息类型：0.普通文本消息，1.图文消息，2.markdown消息，3.富文本消息
     */
    @TableField(value = "text_type")
    private Integer textType;

    /**
     * 文本消息内容长度限制：3000字符
     */
    @TableField(value = "length")
    private Integer length;

    /**
     * 
     */
    @TableField(value = "message_id")
    private Long messageId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}