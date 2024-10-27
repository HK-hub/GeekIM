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
 * @TableName tb_annex
 */
@TableName(value ="tb_annex")
@Data
public class Annex implements Serializable {
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
     * 附件属主：笔记，说说，公众号文章，订阅号文章，消息附件等。
     */
    @TableField(value = "belong_id")
    private Long belongId;

    /**
     * 属主类型：1.笔记，2.说说，3.公众号文章，4.订阅号文章，5.消息附件
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 文件后缀
     */
    @TableField(value = "suffix")
    private String suffix;

    /**
     * 文件大小：单位字节byte
     */
    @TableField(value = "size")
    private Integer size;

    /**
     * 文件原名
     */
    @TableField(value = "original_name")
    private String originalName;

    /**
     * 文件链接
     */
    @TableField(value = "url")
    private String url;

    /**
     * 文件名称
     */
    @TableField(value = "file_name")
    private String fileName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}