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
 * @TableName tb_cloud
 */
@TableName(value ="tb_cloud")
@Data
public class Cloud implements Serializable {
    /**
     * 云盘空间id
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
     * 用户云空间大小,单位字节byte，默认1GB=1024MB = 1024 * 1024 * 1024 byte
     */
    @TableField(value = "total_space")
    private Integer totalSpace;

    /**
     * 已使用空间
     */
    @TableField(value = "used_space")
    private Integer usedSpace;

    /**
     * 剩余可用空间
     */
    @TableField(value = "available_space")
    private Integer availableSpace;

    /**
     * 云空间文件数量限制
     */
    @TableField(value = "file_count_limit")
    private Integer fileCountLimit;

    /**
     * 文件类型限制
     */
    @TableField(value = "file_type_limit")
    private String fileTypeLimit;

    /**
     * 存储信息说明
     */
    @TableField(value = "storage_info")
    private String storageInfo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}