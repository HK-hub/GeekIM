package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 资源表
 * @TableName tb_storage_resource
 */
@TableName(value ="tb_storage_resource")
@Data
public class StorageResource implements Serializable {
    /**
     * 资源id
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
     * 资源属主id
     */
    @TableField(value = "belong_id")
    private Long belongId;

    /**
     * 上传id
     */
    @TableField(value = "upload_id")
    private Long uploadId;

    /**
     * 父文件，父目录，父资源id
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 是否文件夹
     */
    @TableField(value = "folder")
    private Boolean folder;

    /**
     * 云资源路径
     */
    @TableField(value = "cloud_path")
    private String cloudPath;

    /**
     * 存储介质存储路径
     */
    @TableField(value = "storage_path")
    private String storagePath;

    /**
     * 资源类型：1.图片，2.视频，3.音频，4.压缩包，5.文件
     */
    @TableField(value = "resource_type")
    private Integer resourceType;

    /**
     * 资源名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 资源描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 资源扩展名(文件扩展名)
     */
    @TableField(value = "extend_type")
    private String extendType;

    /**
     * 文件url链接
     */
    @TableField(value = "url")
    private String url;

    /**
     * 文件大小：单位byte 字节
     */
    @TableField(value = "size")
    private Long size;

    /**
     * 文件hash值
     */
    @TableField(value = "hash")
    private String hash;

    /**
     * 资源文件md5
     */
    @TableField(value = "md5")
    private String md5;

    /**
     * 文件资源引用计数器：==0时进行删除
     */
    @TableField(value = "reference_count")
    private Integer referenceCount;

    /**
     * 访问计数
     */
    @TableField(value = "access_count")
    private Integer accessCount;

    /**
     * 下载计数
     */
    @TableField(value = "download_count")
    private Integer downloadCount;

    /**
     * 分享状态
     */
    @TableField(value = "share_status")
    private Integer shareStatus;

    /**
     * 访问权限：1.可查看，2.可下载，3.可编辑
     */
    @TableField(value = "access_permission")
    private Integer accessPermission;

    /**
     * 标签集合
     */
    @TableField(value = "tag")
    private String tag;

    /**
     * 资源版本
     */
    @TableField(value = "version")
    private Integer version;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}