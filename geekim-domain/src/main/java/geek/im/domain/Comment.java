package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 评论表
 * @TableName tb_comment
 */
@TableName(value ="tb_comment")
@Data
public class Comment implements Serializable {
    /**
     * 评论id
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
     * 评论者id
     */
    @TableField(value = "commentator_id")
    private Long commentatorId;

    /**
     * 被评论文章，说说，笔记，公众号，社区等可被评论的对象的作者id
     */
    @TableField(value = "author_id")
    private Long authorId;

    /**
     * 文章id
     */
    @TableField(value = "article_id")
    private Long articleId;

    /**
     * 父评论id
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 被评论文章，说说，笔记，公众号，社区等可被评论的对象id
     */
    @TableField(value = "commentated_id")
    private Long commentatedId;

    /**
     * 评论级别：用于热评，精品评论等
     */
    @TableField(value = "level")
    private Integer level;

    /**
     * 评论内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 超文本链接：例如图片，文件，表情等
     */
    @TableField(value = "url")
    private String url;

    /**
     * 被评论对象的类型：被评论文章，说说，笔记，公众号，社区等
     */
    @TableField(value = "type")
    private Integer type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}