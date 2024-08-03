package com.geek.im.server.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

/**
 * @author : HK意境
 * @ClassName : LocalSocketUtil
 * @date : 2024/4/26 22:47
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
public class LocalSocketUtil {

    /**
     * 获取本机地址
     *
     * @return
     */
    public static String getLocalHost() {

        try {
            return getLocalAddressAsString();
        } catch (Exception e) {
            log.error("IP地址获取失败" + e.toString());
        }

        return null;
    }


    private static String getLocalAddressAsString() throws UnknownHostException, SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

        while (interfaces != null && interfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface) interfaces.nextElement();
            if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                continue;
            }
            Enumeration<InetAddress> addresses = netInterface.getInetAddresses();

            while (addresses != null && addresses.hasMoreElements()) {
                InetAddress address = (InetAddress) addresses.nextElement();
                if (acceptableAddress(address)) {
                    return address.getHostAddress();
                }
            }
        }

        throw new UnknownHostException();
    }

    private static boolean acceptableAddress(InetAddress address) {
        return address != null && !address.isLoopbackAddress() && !address.isAnyLocalAddress() && !address.isLinkLocalAddress();
    }


    /**
     * 获取本机空闲端口
     *
     * @return
     */
    public static int getRandomFreePort() {

        return getAvailableTcpPort();
    }


    /**
     * 获取可用的tcp端口号
     *
     * @return
     */
    public static int getAvailableTcpPort() {
        // 指定范围10000到65535
        for (int i = 10000; i <= 65535; i++) {
            try {
                new ServerSocket(i).close();
                return i;
            } catch (IOException e) { // 抛出异常表示不可以，则进行下一个
                continue;
            }
        }

        return 0;
    }


    public static boolean isAvailablePort(int port) {
        try {
            new ServerSocket(port).close();
            return true;
        } catch (IOException e) { // 抛出异常表示不可以，则进行下一个
            return false;
        }
    }

}
