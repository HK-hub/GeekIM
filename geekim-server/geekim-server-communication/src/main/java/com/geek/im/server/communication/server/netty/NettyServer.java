package com.geek.im.server.communication.server.netty;

import com.geek.im.server.communication.server.AbstractServer;
import com.geek.im.server.communication.server.WebSocketServer;
import com.geek.im.server.domain.property.IMServerProperties;
import geek.im.server.common.util.InetAddressUtil;
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

import java.net.InetAddress;
import java.net.InetSocketAddress;
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

    }

    public NettyServer(String host, int port) {
        super(host, port);
    }

    public NettyServer(IMServerProperties serverProperties) {
        super(serverProperties);
        this.serverProperties = serverProperties;
    }

    public NettyServer(String host, int port, IMServerProperties serverProperties) {
        super(serverProperties);
        serverProperties.getServer().setHost(host);
        serverProperties.getServer().setPort(port);
        this.serverProperties = serverProperties;
    }


    /**
     * 初始化
     */
    @Override
    public void initialize() {

        // 初始化线程组
        boolean enableEpoll = this.serverProperties.getEvent().isEnableEpoll();
        initEventLoopGroup(enableEpoll);
        // 初始化启动器
        initServerBootstrap(enableEpoll);

        // 设置Option选型
        this.setChannelOptions(this.channelOptionMap);

        // 设置pipeline handler
        this.setPipelineHandler(this.pipelineHandler);

        log.info("Netty server address location {} will initialize...", this.serverProperties);
    }


    /**
     * 设置ChannelHandler 初始化器,进行初始化ChannelHandler
     *
     * @param pipelineHandler
     */
    protected void setPipelineHandler(ChannelHandler pipelineHandler) {

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

        channelOptionMap.forEach((option, value) -> this.serverBootstrap.option(option, value));
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
            int bossThreads = this.serverProperties.getEvent().getBoss();

            if (enableEpoll) {
                this.bossGroup = new EpollEventLoopGroup(bossThreads);
            } else {
                this.bossGroup = new NioEventLoopGroup(bossThreads);
            }
            log.info("initialized bossGroup threads={}", bossThreads);
        }

        if (Objects.isNull(this.workerGroup)) {
            int workerThreads = this.serverProperties.getEvent().getWorker();
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
    public boolean start() throws Exception {

        String host = this.serverProperties.getServer().getHost();
        Integer port = this.serverProperties.getServer().getPort();
        if (BooleanUtils.isTrue(this.started)) {
            log.info("Netty server address location {}:{} is already started", host, port);
            this.ready = true;
            this.stopped = false;
            this.running = true;
            return false;
        }

        // 进行初始化
        this.initialize();

        // 启动
        boolean run = this.run();
        if (BooleanUtils.isFalse(run)) {
            log.info("Netty server address location {}:{} start failed", host, port);
            this.ready = false;
            this.started = false;
            this.stopped = true;
            this.running = false;
            return false;
        }

        // 启动成功
        this.started = true;
        this.ready = true;
        this.running = true;
        log.info("Netty server address location {}:{} started successfully", host, port);
        return true;
    }


    /**
     * 启动
     *
     * @return
     */
    private boolean run() {

        // 获取本机真实地址
        String host = this.serverProperties.getServer().getHost();
        Integer port = this.serverProperties.getServer().getPort();

        try {
            // 绑定端口: 回填端口为0的情况，会自动分配一个空闲端口
            InetSocketAddress inetSocketAddress = new InetSocketAddress(host, port);
            port = inetSocketAddress.getPort();
            host = inetSocketAddress.getAddress().getHostAddress();

            // 如果是本地地址需要处理成为真是地址
            if (inetSocketAddress.getAddress().isLoopbackAddress() || host.equalsIgnoreCase("localhost")) {
                // 本地地址
                InetAddress inetAddress = InetAddressUtil.getLocalHostExactAddress();
                if (Objects.nonNull(inetAddress)) {
                    host = inetAddress.getHostAddress();
                }
            }
            this.serverProperties.getServer().setHost(host);
            this.serverProperties.getServer().setPort(port);

            log.info("Netty server address location {}:{} will start...", host, port);

            // 绑定
            ChannelFuture channelFuture = serverBootstrap.bind(inetSocketAddress).sync();
            this.serverChannel = channelFuture.channel();

            // 服务启动成功
            log.info("netty server started successfully.ip={},port={}, service name={}", host, port, this.getName());
            channelFuture.addListener(future -> {
                if (BooleanUtils.isFalse(future.isSuccess())) {
                    this.shutdown();
                }
            });
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

        boolean workerGroupTerminated = this.workerGroup.isTerminated();
        boolean bossGroupTerminated = this.bossGroup.isTerminated();

        String host = this.serverProperties.getServer().getHost();
        Integer port = this.serverProperties.getServer().getPort();
        log.info("Netty server address location {}:{} will shutdown...", host, port);

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
        if (!bossGroupTerminated) {
            log.warn("Forcing shutdown of boss event loop...");
            this.bossGroup.shutdownGracefully(0L, 0L, TimeUnit.MILLISECONDS);
        }
        if (!workerGroupTerminated) {
            log.warn("Forcing shutdown of worker event loop...");
            this.workerGroup.shutdownGracefully(0L, 0L, TimeUnit.MILLISECONDS);
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
        this.started = false;
        this.stopped = true;

        // 发送服务器关闭事件

        // TODO 进行统计信息输出：例如：IO字节数，读取消息数，连接数、请求数、响应数、错误数、响应时间
    }

}
