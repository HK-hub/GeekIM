package com.geek.im.authorization.domain.value;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : HK意境
 * @ClassName : SmsCaptchaParam
 * @date : 2024/3/31 15:40
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@Accessors(chain = true)
public class SmsCaptchaParam {

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$", message = "手机号格式错误")
    private String phone;

    /**
     * 短信验证码用处：1.登录，2.注册，3.找回密码，4.修改密码，5.解绑/绑定，6.其他
     */
    private Integer type;
}
