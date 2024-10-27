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
 * @TableName tb_app_monitor_log
 */
@TableName(value ="tb_app_monitor_log")
@Data
public class AppMonitorLog implements Serializable {
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
     * 小程序id
     */
    @TableField(value = "app_id")
    private Long appId;

    /**
     * 订阅者，关注者id
     */
    @TableField(value = "follower_id")
    private Long followerId;

    /**
     * 小程序的api key id
     */
    @TableField(value = "api_key_id")
    private Long apiKeyId;

    /**
     * api 端点
     */
    @TableField(value = "api_endpoint")
    private String apiEndpoint;

    /**
     * 请求方法
     */
    @TableField(value = "request_method")
    private String requestMethod;

    /**
     * 请求时间
     */
    @TableField(value = "request_timestamp")
    private LocalDateTime requestTimestamp;

    /**
     * 响应状态码
     */
    @TableField(value = "response_status")
    private Integer responseStatus;

    /**
     * 
     */
    @TableField(value = "response_timestamp")
    private LocalDateTime responseTimestamp;

    /**
     * 请求参数
     */
    @TableField(value = "request_parameter")
    private Object requestParameter;

    /**
     * 响应数据
     */
    @TableField(value = "response_data")
    private Object responseData;

    /**
     * 客户端地址
     */
    @TableField(value = "client_address")
    private String clientAddress;

    /**
     * 小程序服务器地址
     */
    @TableField(value = "remote_address")
    private String remoteAddress;

    /**
     * 动作类型
     */
    @TableField(value = "action_type")
    private String actionType;

    /**
     * 类型描述
     */
    @TableField(value = "action_description")
    private String actionDescription;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}