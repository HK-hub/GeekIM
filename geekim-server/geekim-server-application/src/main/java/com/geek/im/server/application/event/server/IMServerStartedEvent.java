package com.geek.im.server.application.event.server;

import com.geek.im.server.domain.property.IMServerProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : HK意境
 * @ClassName : IMServerStartedEvent
 * @date : 2024/4/29 19:13
 * @description : IM服务器启动成功事件
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@Accessors(chain = true)
public class IMServerStartedEvent {

    private IMServerProperties serverProperties;

    public IMServerStartedEvent() {
    }

    public IMServerStartedEvent(IMServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }
}
