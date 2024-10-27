package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 消息序列表
 * @TableName tb_sequence
 */
@TableName(value ="tb_sequence")
@Data
public class Sequence implements Serializable {
    /**
     * 序列id
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
     * 序列器名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 序列器描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 会话id
     */
    @TableField(value = "conversation_id")
    private Long conversationId;

    /**
     * 接收者id
     */
    @TableField(value = "receiver_id")
    private Long receiverId;

    /**
     * 发送者id
     */
    @TableField(value = "sender_id")
    private Long senderId;

    /**
     * 群组id
     */
    @TableField(value = "group_id")
    private Long groupId;

    /**
     * 号段最大值，表示下一次分配号段的开始值
     */
    @TableField(value = "max")
    private Long max;

    /**
     * 步长
     */
    @TableField(value = "step")
    private Integer step;

    /**
     * 号段长度
     */
    @TableField(value = "segment")
    private Integer segment;

    /**
     * 版本
     */
    @TableField(value = "version")
    private Integer version;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}