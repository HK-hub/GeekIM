package com.geek.im.common.response;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : HK意境
 * @ClassName : UploadResponse
 * @date : 2023/1/1 13:31
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
public class UploadResponse {

    // 是否成功
    private boolean success;
    // 返回码
    private Integer code;
    // 返回信息
    private String message;
    // oss返回数据:url
    private String oss;
    // 网关(nginx 等) 链接
    private String gateway;
    // 响应时间
    private LocalDateTime dateTime = LocalDateTime.now();

    public UploadResponse(String oss, String gateway) {
        this.oss = oss;
        this.gateway = gateway;
    }
}
