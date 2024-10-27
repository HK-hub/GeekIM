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
 * @TableName tb_article
 */
@TableName(value ="tb_article")
@Data
public class Article implements Serializable {
    /**
     * 
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
     * 作者id
     */
    @TableField(value = "author_id")
    private Long authorId;

    /**
     * 公众号id
     */
    @TableField(value = "account_id")
    private Long accountId;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 文章内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 文章类型
     */
    @TableField(value = "content_type")
    private Integer contentType;

    /**
     * 从那篇文章复制，转载，转发而来
     */
    @TableField(value = "from_id")
    private Long fromId;

    /**
     * 图片内容，封面等
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
     * 分享转发数量
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
     * 文章类型：1.公众号文章，2.订阅号文章
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 计划发布时间
     */
    @TableField(value = "plan_publish_time")
    private LocalDateTime planPublishTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}