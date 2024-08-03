package com.geek.im.server.bootstrap.starter;

import com.geek.im.server.communication.server.instance.IMServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author : HK意境
 * @ClassName : IMCommunicationStarter
 * @date : 2024/8/2 8:58
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Async
@Slf4j
@Component
public class IMCommunicationStarter implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // 启动IM服务器
        IMServer imServer = applicationContext.getBean(IMServer.class);
        try {
            imServer.start();
        } catch (Exception e) {
            log.error("IM通讯服务器启动失败：exception=", e);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
