package com.geek.im.server.communication.server;

import com.geek.im.server.domain.property.IMServerProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * @author : HK意境
 * @ClassName : AbstractServer
 * @date : 2024/4/26 21:18
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
@Setter
@ToString
public abstract class AbstractServer implements Server {

    protected IMServerProperties serverProperties = new IMServerProperties();

    protected boolean started;

    protected boolean ready;

    protected boolean running;

    protected boolean stopped;

    public AbstractServer() {

    }

    public AbstractServer(String host, int port) {
        this.serverProperties.getServer().setHost(host);
        this.serverProperties.getServer().setPort(port);
    }

    public AbstractServer(IMServerProperties serverProperties) {

        Objects.requireNonNull(serverProperties);
        this.serverProperties = serverProperties;
    }

    /**
     * 获取服务器定位key
     *
     * @return
     */
    public String getServerLocation() {

        return this.serverProperties.buildServerLocation();
    }


    /**
     * 服务名称
     *
     * @return
     */
    @Override
    public String getName() {

        String host = this.serverProperties.getServer().getHost();
        Integer port = this.serverProperties.getServer().getPort();
        String path = this.serverProperties.getServer().getPath();
        String protocol = this.serverProperties.getServer().getProtocol();

        return protocol + "://" + host + ":" + port + path;
    }

    /**
     * 服务域名或IP地址
     *
     * @return
     */
    @Override
    public String getHost() {
        return this.serverProperties.getServer().getHost();
    }

    /**
     * 服务端口
     *
     * @return
     */
    @Override
    public int getPort() {
        return this.serverProperties.getServer().getPort();
    }

    /**
     * 服务IP地址
     *
     * @return
     */
    @Override
    public String getAddress() {
        return this.serverProperties.getServer().getHost();
    }


    /**
     * 支持的协议
     *
     * @return
     */
    @Override
    public String protocol() {
        return this.serverProperties.getServer().getProtocol();
    }

    /**
     * 初始化
     */
    public abstract void initialize();


    @Override
    public boolean start() throws Exception {
        throw new UnsupportedOperationException(this.getClass().getName() + " is not a available WebSocket Server");
    }

    @Override
    public void shutdown() {
        throw new UnsupportedOperationException(this.getClass().getName() + " is not a available WebSocket Server");
    }
}
