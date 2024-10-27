package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 分片文件上传表
 * @TableName tb_split_upload
 */
@TableName(value ="tb_split_upload")
@Data
public class SplitUpload implements Serializable {
    /**
     * 分片上传id
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
     * 文件是否删除[0:否;1:是;]
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
     * 文件属性[1:合并文件;2:拆分文件]
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 驱动类型[1:local;2:cos;]
     */
    @TableField(value = "drive")
    private Integer drive;

    /**
     * 临时文件hash名，md5
     */
    @TableField(value = "upload_id")
    private String uploadId;

    /**
     * 上传的用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 原文件名
     */
    @TableField(value = "original_name")
    private String originalName;

    /**
     * 当前索引块
     */
    @TableField(value = "split_index")
    private Integer splitIndex;

    /**
     * 总上传索引块
     */
    @TableField(value = "split_num")
    private Integer splitNum;

    /**
     * 临时保存路径
     */
    @TableField(value = "path")
    private String path;

    /**
     * 文件后缀名
     */
    @TableField(value = "file_extension")
    private String fileExtension;

    /**
     * 文件大小
     */
    @TableField(value = "file_size")
    private Integer fileSize;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}