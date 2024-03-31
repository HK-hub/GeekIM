package com.geek.im.user.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName : UserInfoDTO
 * @date : 2024/3/29 20:53
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@Accessors(chain = true)
public class UserInfoDTO {

    private String username;

    private String email;

    private String nickname;

    private String avatar;

    private Integer age;

}
