package com.geek.im.server.domain.event.connect;

import com.geek.im.server.domain.aggregate.UserInfo;
import com.geek.im.server.domain.value.ClientConnectRequest;
import com.geek.im.server.domain.value.ClientDevice;
import io.netty.channel.Channel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : HK意境
 * @ClassName : UserConnectedEvent
 * @date : 2024/8/3 20:49
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@Accessors(chain = true)
public class UserDisconnectedEvent {

    private ClientConnectRequest connectRequest;

    private UserInfo userInfo;

    private ClientDevice clientDevice;

    private Channel channel;

}
