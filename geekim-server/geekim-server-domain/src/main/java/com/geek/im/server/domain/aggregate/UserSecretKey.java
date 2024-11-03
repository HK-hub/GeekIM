package com.geek.im.server.domain.aggregate;

import lombok.Data;

/**
 * @author : HK意境
 * @ClassName : UserSecretKey
 * @date : 2024/8/30 21:02
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
public class UserSecretKey {

    private Long userId;

    /**
     * 私钥
     */
    private String privateKey;

}
