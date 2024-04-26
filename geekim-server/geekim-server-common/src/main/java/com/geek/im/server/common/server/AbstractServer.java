package com.geek.im.server.common.server;

import com.geek.im.server.common.config.ServerPropertySetting;
import com.geek.im.server.common.protocol.connect.ConnectProtocol;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@NoArgsConstructor
public abstract class AbstractServer implements Server {

    private String name;

    protected String host;

    protected int port;

    /**
     * 服务器配置
     */
    protected ServerPropertySetting setting;

    protected ConnectProtocol connectProtocol;

    protected boolean started;

    protected boolean ready;

    protected boolean running;

    public AbstractServer(String host, int port) {
        this.host = host;
        this.port = port;
    }


    /**
     * 获取服务器定位key
     *
     * @return
     */
    public String getServerLocation() {

        return this.host + ":" + this.port;
    }


    /**
     * 服务IP地址
     *
     * @return
     */
    @Override
    public String getAddress() {
        return this.host;
    }


    /**
     * 支持的协议
     *
     * @return
     */
    @Override
    public String protocol() {
        return this.connectProtocol.getCode();
    }

    /**
     * 初始化
     */
    public abstract void initialize();


    @Override
    public boolean start() {
        throw new UnsupportedOperationException(this.getClass().getName() + " is not a available WebSocket Server");
    }

    @Override
    public void shutdown() {
        throw new UnsupportedOperationException(this.getClass().getName() + " is not a available WebSocket Server");
    }
}
