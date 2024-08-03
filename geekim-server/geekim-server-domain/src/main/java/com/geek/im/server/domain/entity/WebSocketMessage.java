package com.geek.im.server.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : HK意境
 * @ClassName : WebSocketMessage
 * @date : 2024/6/7 17:31
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@Accessors(chain = true)
public class WebSocketMessage {

    private Integer actionType;

    private String data;

}
