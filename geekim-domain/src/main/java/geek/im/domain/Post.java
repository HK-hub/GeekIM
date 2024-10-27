package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 说说表
 * @TableName tb_post
 */
@TableName(value ="tb_post")
@Data
public class Post implements Serializable {
    /**
     * 说说id
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
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 文本内容
     */
    @TableField(value = "text_content")
    private String textContent;

    /**
     * 内容类型：1.markdown，2.html,3.富文本
     */
    @TableField(value = "content_type")
    private Integer contentType;

    /**
     * 复制，克隆，转发与那篇文章
     */
    @TableField(value = "from_id")
    private Long fromId;

    /**
     * 图片内容
     */
    @TableField(value = "image_content")
    private String imageContent;

    /**
     * 浏览量
     */
    @TableField(value = "view_num")
    private Integer viewNum;

    /**
     * 点赞量
     */
    @TableField(value = "like_num")
    private Integer likeNum;

    /**
     * 转发量，分享量
     */
    @TableField(value = "share_num")
    private Integer shareNum;

    /**
     * 收藏量
     */
    @TableField(value = "collect_num")
    private Integer collectNum;

    /**
     * 评论量
     */
    @TableField(value = "comment_num")
    private Integer commentNum;

    /**
     * 计划发布时间
     */
    @TableField(value = "publish_time")
    private LocalDateTime publishTime;

    /**
     * 可见用户id集合
     */
    @TableField(value = "visible_user_id_list")
    private Long visibleUserIdList;

    /**
     * 不可见用户列表
     */
    @TableField(value = "invisible_user_id_list")
    private Long invisibleUserIdList;

    /**
     * @好友，@用户集合
     */
    @TableField(value = "mentioned_user_id_list")
    private Long mentionedUserIdList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}