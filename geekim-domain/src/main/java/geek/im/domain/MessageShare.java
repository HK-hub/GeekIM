package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 分享消息表
 * @TableName tb_message_share
 */
@TableName(value ="tb_message_share")
@Data
public class MessageShare implements Serializable {
    /**
     * 
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
     * 消息id
     */
    @TableField(value = "message_id")
    private Long messageId;

    /**
     * 分享类型：1.笔记，2.说说，3.文章,4.投票，5.抽奖，6.直播间，7.会议等
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 分享标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 标签列表
     */
    @TableField(value = "tag_id_list")
    private Long tagIdList;

    /**
     * 分类id
     */
    @TableField(value = "category_id")
    private Long categoryId;

    /**
     * 被分享的对象id
     */
    @TableField(value = "sharable_id")
    private Long sharableId;

    /**
     * 分享者
     */
    @TableField(value = "share_user_id")
    private Long shareUserId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}