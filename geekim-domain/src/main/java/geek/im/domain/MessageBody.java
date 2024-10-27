package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 消息体表
 * @TableName tb_message_body
 */
@TableName(value ="tb_message_body")
@Data
public class MessageBody implements Serializable {
    /**
     * 聊天消息表id
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
     * 是否删除该条聊天记录,0.false, 1.ture
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
     * 扩展字段，一般使用JSON字符串存储,可以用户回复消息，@消息，超文本消息，卡片消息，视频消息等
     */
    @TableField(value = "extra")
    private Object extra;

    /**
     * 
     */
    @TableField(value = "attribute")
    private Object attribute;

    /**
     * 消息序列号
     */
    @TableField(value = "sequence")
    private Long sequence;

    /**
     * 消息属性：0.默认，1.离线消息，2.漫游消息，3.同步消息，4.透传消息，5.控制消息
     */
    @TableField(value = "message_feature")
    private Integer messageFeature;

    /**
     * 消息类型:1.文本，2.图片，3.语音，4.图文混合，5.文件，6.语音通话，7.视频通话，
                                8.白板演示，9.远程控制，10.日程安排，11.外部分享,12.@消息，13.红包消息
     */
    @TableField(value = "message_type")
    private Integer messageType;

    /**
     * 消息内容,最大文本数量1024个字符
     */
    @TableField(value = "content")
    private String content;

    /**
     * 图片，文件，视频，音频等等链接
     */
    @TableField(value = "url")
    private String url;

    /**
     * 附件列表
     */
    @TableField(value = "attachment_id_list")
    private Long attachmentIdList;

    /**
     * 回复消息id
     */
    @TableField(value = "replied_id")
    private Long repliedId;

    /**
     * 转发自那条消息id
     */
    @TableField(value = "forward_id")
    private Long forwardId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}