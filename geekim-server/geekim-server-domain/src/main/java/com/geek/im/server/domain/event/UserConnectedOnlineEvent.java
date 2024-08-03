package com.geek.im.server.domain.event;

import com.geek.im.server.domain.aggregate.UserInfo;
import com.geek.im.server.domain.value.ClientDevice;
import lombok.Data;
import lombok.experimental.Accessors;

import java.nio.channels.Channel;

/**
 * @author : HK意境
 * @ClassName : UserConnectedOnlineEvent
 * @date : 2024/8/3 20:49
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@Accessors(chain = true)
public class UserConnectedOnlineEvent {

    private UserInfo userInfo;

    private ClientDevice clientDevice;

    private Channel channel;

}
