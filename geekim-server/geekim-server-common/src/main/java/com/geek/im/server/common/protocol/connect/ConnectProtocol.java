package com.geek.im.server.common.protocol.connect;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

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

    public static ConnectProtocol of(String protocol) {

        if (StringUtils.isEmpty(protocol)) {
            return null;
        }

        for (ConnectProtocol value : values()) {
            if (value.getCode().equalsIgnoreCase(protocol)) {
                return value;
            }
        }

        return null;
    }
}
