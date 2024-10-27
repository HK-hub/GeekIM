package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 群聊公告表
 * @TableName tb_group_announcement
 */
@TableName(value ="tb_group_announcement")
@Data
public class GroupAnnouncement implements Serializable {
    /**
     * 公告id
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
     * 群id
     */
    @TableField(value = "group_id")
    private Long groupId;

    /**
     * 群号
     */
    @TableField(value = "group_account")
    private Long groupAccount;

    /**
     * 群公告类型：1.普通公告，2.置顶公告, 3.新人公告
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 公告撰写者
     */
    @TableField(value = "author")
    private Long author;

    /**
     * 公告标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 公告内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 公告附件id集合
     */
    @TableField(value = "attachment_id_list")
    private String attachmentIdList;

    /**
     * 是否置顶
     */
    @TableField(value = "top")
    private Boolean top;

    /**
     * 公告排序顺序，越低越靠前
     */
    @TableField(value = "order")
    private Integer order;

    /**
     * 过期时间
     */
    @TableField(value = "expiry_time")
    private LocalDateTime expiryTime;

    /**
     * 浏览量
     */
    @TableField(value = "view_count")
    private Integer viewCount;

    /**
     * 点赞量
     */
    @TableField(value = "like_count")
    private Integer likeCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}