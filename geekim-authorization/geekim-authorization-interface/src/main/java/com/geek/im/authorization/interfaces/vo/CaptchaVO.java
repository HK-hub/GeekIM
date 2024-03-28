package com.geek.im.authorization.interfaces.vo;

import lombok.Data;

/**
 * @author : HK意境
 * @ClassName : CaptchaVO
 * @date : 2024/3/28 19:13
 * @description : 图像验证码
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
public class CaptchaVO {

    private String key;

    private String code;
}
