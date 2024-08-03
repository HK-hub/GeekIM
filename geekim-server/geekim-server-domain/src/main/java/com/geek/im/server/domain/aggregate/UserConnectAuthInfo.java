package com.geek.im.server.domain.aggregate;

import com.geek.im.server.domain.value.ClientDevice;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : HK意境
 * @ClassName : UserConnectAuthInfo
 * @date : 2024/8/3 20:08
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@Accessors(chain = true)
public class UserConnectAuthInfo {

    private UserInfo userInfo;

    private ClientDevice clientDevice;
}
