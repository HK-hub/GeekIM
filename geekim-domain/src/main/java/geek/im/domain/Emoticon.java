package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 表情包表
 * @TableName tb_emoticon
 */
@TableName(value ="tb_emoticon")
@Data
public class Emoticon implements Serializable {
    /**
     * 表情包id
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
     * 表情类型：1.系统，2.自定义，3.导入，4.收藏
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 表情内容：文本，url
     */
    @TableField(value = "content")
    private String content;

    /**
     * 表情链接
     */
    @TableField(value = "url")
    private String url;

    /**
     * 表情关键词
     */
    @TableField(value = "keyword")
    private String keyword;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}