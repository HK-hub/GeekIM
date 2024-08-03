package com.geek.im.server.communication.server;

/**
 * @author : HK意境
 * @ClassName : Server
 * @date : 2024/4/26 21:11
 * @description : server服务器
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface Server {

    /**
     * 服务名称
     *
     * @return
     */
    public String getName();

    /**
     * 服务域名或IP地址
     *
     * @return
     */
    public String getHost();

    /**
     * 服务IP地址
     *
     * @return
     */
    public String getAddress();

    /**
     * 服务端口
     *
     * @return
     */
    public int getPort();

    /**
     * 支持的协议
     *
     * @return
     */
    public String protocol();

    public boolean isRunning();

    public boolean isReady();

    public boolean start() throws Exception;

    public void shutdown();
}
