package com.geek.im.server.common.server.netty;

import com.geek.im.server.common.config.ServerPropertyEntry;
import com.geek.im.server.common.config.ServerPropertySetting;
import com.geek.im.server.common.handler.DefaultChannelHandlerInitializer;
import com.geek.im.server.common.protocol.connect.ConnectProtocol;
import com.geek.im.server.common.server.AbstractServer;
import com.geek.im.server.common.server.WebSocketServer;
import com.geek.im.server.common.util.LocalSocketUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author : HK意境
 * @ClassName : NettyServer
 * @date : 2024/4/26 21:37
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
public class NettyServer extends AbstractServer implements WebSocketServer {

    @Getter
    protected Channel serverChannel;

    private Class<? extends Channel> channelClass;

    /**
     * channel选项
     */
    protected Map<ChannelOption<Object>, Object> channelOptionMap;

    protected ChannelHandler pipelineHandler;

    @Getter
    @Setter
    protected EventLoopGroup workerGroup;

    @Getter
    @Setter
    protected EventLoopGroup bossGroup;

    @Getter
    @Setter
    protected ServerBootstrap serverBootstrap;

    public NettyServer() {
        // 使用本地地址，随机端口
        this.host = LocalSocketUtil.getLocalHost();
        this.port = LocalSocketUtil.getRandomFreePort();
        this.connectProtocol = ConnectProtocol.WEBSOCKET;
    }

    public NettyServer(String host, int port) {
        super(host, port);
        this.connectProtocol = ConnectProtocol.WEBSOCKET;
    }

    /**
     * 初始化
     */
    @Override
    public void initialize() {

        if (Objects.isNull(this.setting)) {
            this.setting = ServerPropertySetting.defaultSetting();
        }

        // 网络IO模型
        boolean enableEpoll = this.setting.getAsBoolean(ServerPropertyEntry.EPOLL_MODEL.getKey());
        // 初始化线程组
        initEventLoopGroup(enableEpoll);
        // 初始化启动器
        initServerBootstrap(enableEpoll);

        // 设置Option选型
        this.setChannelOptions(this.channelOptionMap);

        // 设置pipeline handler
        this.setPipelineHandler(this.pipelineHandler);

        log.info("Netty server address location {}:{} will initialize...", this.host, this.port);
    }


    /**
     * 设置ChannelHandler 初始化器,进行初始化ChannelHandler
     *
     * @param pipelineHandler
     */
    protected void setPipelineHandler(ChannelHandler pipelineHandler) {

        if (Objects.isNull(pipelineHandler)) {
            pipelineHandler = new DefaultChannelHandlerInitializer();
        }

        this.serverBootstrap.childHandler(pipelineHandler);
    }


    /**
     * 设置netty参数选项
     *
     * @param channelOptionMap
     * @param <T>
     */
    protected <T> void setChannelOptions(Map<ChannelOption<Object>, Object> channelOptionMap) {

        if (MapUtils.isEmpty(channelOptionMap)) {
            this.serverBootstrap
                    // TCP_NODELAY算法，尽可能发送大块数据，减少充斥的小块数据
                    .option(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                    // 半连接队列+全连接队列数量
                    .option(ChannelOption.SO_BACKLOG, 2048)
                    // 保持长连接
                    .option(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);
            return;
        }

        channelOptionMap.forEach((option, value) -> {
            this.serverBootstrap.option(option, value);
        });
    }

    /**
     * 初始化启动器
     *
     * @param enableEpoll
     */
    private void initServerBootstrap(boolean enableEpoll) {
        if (Objects.isNull(this.serverBootstrap)) {
            this.serverBootstrap = new ServerBootstrap();
            this.serverBootstrap.group(bossGroup, workerGroup);
        }

        // 初始化Channel
        if (BooleanUtils.isTrue(enableEpoll)) {
            this.channelClass = EpollServerSocketChannel.class;
            this.serverBootstrap.channel(EpollServerSocketChannel.class);
        } else {
            this.channelClass = NioServerSocketChannel.class;
            this.serverBootstrap.channel(NioServerSocketChannel.class);
        }
    }


    /**
     * 初始化线程组
     *
     * @param enableEpoll
     */
    private void initEventLoopGroup(boolean enableEpoll) {

        log.info("start to initialize bossGroup and workerGroup...");
        if (Objects.isNull(this.bossGroup)) {
            // 线程池配置
            int bossThreads = this.setting.getAsInt(ServerPropertyEntry.BOSS_GROUP_THREADS.getKey());

            if (enableEpoll) {
                this.bossGroup = new EpollEventLoopGroup(bossThreads);
            } else {
                this.bossGroup = new NioEventLoopGroup(bossThreads);
            }
            log.info("initialized bossGroup threads={}", bossThreads);
        }

        if (Objects.isNull(this.workerGroup)) {
            int workerThreads = this.setting.getAsInt(ServerPropertyEntry.WORKER_GROUP_THREADS.getKey());
            if (enableEpoll) {
                this.workerGroup = new EpollEventLoopGroup(workerThreads);
            } else {
                this.workerGroup = new NioEventLoopGroup(workerThreads);
            }
            log.info("initialized worker threads={}", workerThreads);
        }
        log.info("initialize bossGroup and workerGroup successfully");
    }


    /**
     * 启动服务器
     *
     * @return
     */
    @Override
    public boolean start() {

        if (BooleanUtils.isTrue(this.started)) {
            log.info("Netty server address location {}:{} is already started", this.host, this.port);
            return false;
        }

        // 进行初始化
        this.initialize();

        log.info("Netty server address location {}:{} will start...", this.host, this.port);
        this.run();
        // 启动成功
        this.started = true;
        log.info("Netty server address location {}:{} started successfully", this.host, this.port);
        return true;
    }


    /**
     * 启动
     *
     * @return
     */
    public boolean run() {
        try {
            // 绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(this.host, this.port).sync();
            this.serverChannel = channelFuture.channel();
            // 服务启动成功
            log.info("netty server started successfully.ip={},port={}", this.host, this.port);
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();
        } catch (Exception e) {
            log.error("netty server start error...", e);
            this.shutdown();
            return false;
        }

        return true;
    }


    /**
     * 关闭服务器
     */
    @Override
    public void shutdown() {

        log.info("Netty server address location {}:{} will shutdown...", this.host, this.port);

        // 关闭线程池
        if (Objects.isNull(this.workerGroup) && Objects.isNull(this.bossGroup)) {
            log.error("Netty acceptor is not initialized");
            throw new IllegalStateException("Invoked close on an Acceptor that wasn't initialized");
        }

        Future<?> workerGroupShutdownFuture = null;
        Future<?> bossGroupShutdownFuture = null;
        if (Objects.nonNull(this.workerGroup)) {
            workerGroupShutdownFuture = this.workerGroup.shutdownGracefully();
        }

        if (Objects.nonNull(this.bossGroup)) {
            bossGroupShutdownFuture = this.bossGroup.shutdownGracefully();
        }

        log.info("Waiting for worker and boss event loop groups to terminate...");
        try {
            if (Objects.nonNull(workerGroupShutdownFuture)) {
                // 等待worker线程关闭
                workerGroupShutdownFuture.await(15, TimeUnit.SECONDS);
            }
            if (Objects.nonNull(bossGroupShutdownFuture)) {
                // 等待boss线程关闭
                bossGroupShutdownFuture.await(15, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            log.warn("An Exception was caught while waiting for event loops to terminate...");
        }

        // 如果关闭失败，进行强制关闭
        if (!this.workerGroup.isTerminated()) {
            log.warn("Forcing shutdown of worker event loop...");
            this.workerGroup.shutdownGracefully(0L, 0L, TimeUnit.MILLISECONDS);
        }

        if (!this.bossGroup.isTerminated()) {
            log.warn("Forcing shutdown of boss event loop...");
            this.bossGroup.shutdownGracefully(0L, 0L, TimeUnit.MILLISECONDS);
        }

        if (Objects.nonNull(this.serverChannel)) {
            try {
                this.serverChannel.close();
            } catch (Exception e) {
                log.info("netty server channel shutdown failed:", e);
            }
        }

        log.info("netty server shutdown successfully");
        // 设置服务器状态
        this.setReady(false);
        this.setRunning(false);

        // 发送服务器关闭事件

        // TODO 进行统计信息输出：例如：IO字节数，读取消息数，连接数、请求数、响应数、错误数、响应时间
    }


    public static void main(String[] args) {
        NettyServer nettyServer = new NettyServer();
        ServerPropertySetting setting = ServerPropertySetting.defaultSetting();
        setting.set(ServerPropertyEntry.EPOLL_MODEL.getKey(), false);
        nettyServer.setSetting(setting);
        nettyServer.start();
    }

}
