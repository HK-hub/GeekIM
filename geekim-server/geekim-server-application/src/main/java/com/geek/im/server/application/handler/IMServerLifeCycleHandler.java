package com.geek.im.server.application.handler;

import com.geek.im.server.application.event.server.IMServerStartedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author : HK意境
 * @ClassName : IMServerLifeCycleHandler
 * @date : 2024/4/29 19:38
 * @description : IM Server服务器生命周期处理器
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
public class IMServerLifeCycleHandler {


    /**
     * IM服务器启动成功事件
     *
     * @param event
     */
    @Async
    @EventListener(IMServerStartedEvent.class)
    public void onIMServerStarted(IMServerStartedEvent event) {

        log.info("IM Server started successfully, server properties: {}", event.getServerProperties().toString());
    }


}
