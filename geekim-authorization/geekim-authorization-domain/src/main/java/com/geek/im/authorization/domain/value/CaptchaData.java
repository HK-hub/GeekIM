package com.geek.im.authorization.domain.value;

import lombok.Data;

/**
 * @author : HK意境
 * @ClassName : CaptchaData
 * @date : 2024/3/28 19:14
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
public class CaptchaData {

    /**
     * 可能为图像验证码存储key。手机号。邮箱号。用户名等
     */
    private String key;

    private String code;
}
