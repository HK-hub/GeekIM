package geek.im.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 管理员表
 * @TableName tb_admin
 */
@TableName(value ="tb_admin")
@Data
public class Admin implements Serializable {
    /**
     * 管理员id，关联上用户id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 被谁更新
     */
    @TableField(value = "update_by")
    private Long updateBy;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted")
    private Boolean deleted;

    /**
     * 
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 是否启用
     */
    @TableField(value = "enable")
    private Boolean enable;

    /**
     * 扩展信息
     */
    @TableField(value = "extra")
    private Object extra;

    /**
     * 属性
     */
    @TableField(value = "attribute")
    private Object attribute;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 加密密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 管理员账号：邮箱，电话，QQ，微信，Github,Gitee等
     */
    @TableField(value = "account")
    private String account;

    /**
     * 最后一次登录时间
     */
    @TableField(value = "login_time")
    private LocalDateTime loginTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}