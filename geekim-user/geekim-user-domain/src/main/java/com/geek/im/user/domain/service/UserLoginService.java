package com.geek.im.user.domain.service;

import com.geek.im.user.domain.dto.UserLoginDTO;

/**
 * @author : HK意境
 * @ClassName : UserLoginService
 * @date : 2024/2/25 16:04
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface UserLoginService {

    UserLoginDTO login(UserLoginDTO userLoginDTO);

}
