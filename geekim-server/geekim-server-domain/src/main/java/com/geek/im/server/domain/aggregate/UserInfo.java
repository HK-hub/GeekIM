package com.geek.im.server.domain.aggregate;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : HK意境
 * @ClassName : UserInfo
 * @date : 2024/8/3 20:10
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@Accessors(chain = true)
public class UserInfo {

    private Long id;

    private String username;

    private String account;

    private String nickname;

    private String avatar;
}
