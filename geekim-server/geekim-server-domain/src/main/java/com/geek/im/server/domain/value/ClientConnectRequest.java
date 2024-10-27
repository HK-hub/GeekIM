package com.geek.im.server.domain.value;

import geek.im.server.common.enums.DeviceTypeEnum;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;

/**
 * @author : HK意境
 * @ClassName : ClientConnectRequest
 * @date : 2024/8/3 20:12
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@Accessors(chain = true)
public class ClientConnectRequest {

    private ChannelHandlerContext context;

    /**
     * 请求uri
     */
    private String uri;

    private UriComponents uriComponents;

    /**
     * 查询参数
     */
    private MultiValueMap<String, String> queryParamMap;

    /**
     * 请求头
     */
    private HttpHeaders headers;

    /**
     * 请求token
     */
    private String token;

    /**
     * 设备类型
     */
    private DeviceTypeEnum deviceTypeEnum;

}
