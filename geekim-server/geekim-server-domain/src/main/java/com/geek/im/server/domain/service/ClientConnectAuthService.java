package com.geek.im.server.domain.service;

import com.geek.im.server.domain.aggregate.UserConnectAuthInfo;
import com.geek.im.server.domain.value.ClientConnectRequest;

/**
 * @author : HK意境
 * @ClassName : ClientConnectAuthService
 * @date : 2024/8/3 20:06
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface ClientConnectAuthService {

    public UserConnectAuthInfo authConnectClient(ClientConnectRequest request) throws Exception;

}
