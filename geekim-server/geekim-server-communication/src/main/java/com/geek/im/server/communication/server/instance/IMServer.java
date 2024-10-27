package com.geek.im.server.communication.server.instance;

import com.geek.im.server.application.event.server.IMServerStartedEvent;
import com.geek.im.server.communication.handler.WebSocketIMServerChannelInitializer;
import com.geek.im.server.communication.server.netty.NettyServer;
import com.geek.im.server.domain.property.IMServerProperties;
import geek.im.server.common.exception.IMServerException;
import geek.im.server.common.util.LocalSocketUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author : HK意境
 * @ClassName : IMServer
 * @date : 2024/4/27 13:12
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
public class IMServer extends NettyServer implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public IMServer(IMServerProperties serverProperties) {
        super(serverProperties);
        this.pipelineHandler = new WebSocketIMServerChannelInitializer();
    }

    public void setBossGroupThreads(int bossGroupThreads) {
        this.serverProperties.getEvent().setBoss(bossGroupThreads);
    }

    public void setWorkerGroupThreads(int workerGroupThreads) {
        this.serverProperties.getEvent().setWorker(workerGroupThreads);
    }


    public void addChannelOption(ChannelOption<Object> option, Object value) {
        this.channelOptionMap.put(option, value);
    }

    /**
     * 设置handler初始化器
     */
    public void setSocketChannelInitializer(ChannelHandler channelInitializer) {
        this.pipelineHandler = channelInitializer;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 启动条件检查
     * 设置option,handler等
     *
     * @throws Exception
     */
    public void startConditionCheck() throws Exception {

        // 初始化端口，地址等
        Integer port = this.serverProperties.getServer().getPort();
        if (BooleanUtils.isFalse(LocalSocketUtil.isAvailablePort(port))) {
            // 端口不可用，获取一个可用端口
            int randomFreePort = LocalSocketUtil.getRandomFreePort();
            if (randomFreePort == 0) {
                log.error("IM通讯服务启动失败:server info={}, actual port={}", this.serverProperties, port);
                throw new IMServerException("IM通讯服务启动失败:" + port + "端口不可用!");
            }
            port = randomFreePort;
            this.serverProperties.getServer().setPort(port);
        }
    }

    /**
     * 启动服务器
     *
     * @return
     */
    @Override
    public boolean start() throws Exception {

        // 启动条件检查
        startConditionCheck();

        // 启动应用服务器
        boolean start = super.start();

        if (BooleanUtils.isFalse(start)) {
            return false;
        }

        // 启动成功发送事件
        this.applicationContext.publishEvent(new IMServerStartedEvent(this.serverProperties));
        return true;
    }
}
