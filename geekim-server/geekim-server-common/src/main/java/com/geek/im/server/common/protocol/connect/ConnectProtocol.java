package com.geek.im.server.common.protocol.connect;

import lombok.Getter;

/**
 * @author : HK意境
 * @ClassName : ConnectProtocol
 * @date : 2024/4/26 21:30
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
public enum ConnectProtocol {

    UDP("UDP"),
    TCP("TCP"),
    WEBSOCKET("WebSocket"),
    KCP("KCP"),
    ;

    public final String code;

    ConnectProtocol(String code) {
        this.code = code;
    }
}
