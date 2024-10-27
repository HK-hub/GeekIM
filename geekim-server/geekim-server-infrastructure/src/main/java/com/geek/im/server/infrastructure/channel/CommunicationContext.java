package com.geek.im.server.infrastructure.channel;

import com.geek.im.server.domain.aggregate.UserInfo;
import geek.im.server.common.enums.EncryptAlgorithmEnum;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : HK意境
 * @ClassName : CommunicationContext
 * @date : 2024/10/27 19:18
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
@Setter
public class CommunicationContext {

    private Channel channel;

    private ChannelHandlerContext context;

    private UserInfo userInfo;

    private EncryptAlgorithmEnum encryptAlgorithmEnum;

    public CommunicationContext(Channel channel, ChannelHandlerContext context) {
        this.channel = channel;
        this.context = context;
    }
}
