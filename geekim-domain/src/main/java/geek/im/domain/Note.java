package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 笔记表
 * @TableName tb_note
 */
@TableName(value ="tb_note")
@Data
public class Note implements Serializable {
    /**
     * 笔记id
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
     * 笔记标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 摘要
     */
    @TableField(value = "summary")
    private String summary;

    /**
     * markdown 格式内容
     */
    @TableField(value = "md_content")
    private String mdContent;

    /**
     * html 格式内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 笔记封面
     */
    @TableField(value = "cover")
    private String cover;

    /**
     * 作者id
     */
    @TableField(value = "author_id")
    private Long authorId;

    /**
     * 从那篇笔记克隆复制而来
     */
    @TableField(value = "from_id")
    private Long fromId;

    /**
     * 笔记分类id
     */
    @TableField(value = "category_id")
    private Long categoryId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}