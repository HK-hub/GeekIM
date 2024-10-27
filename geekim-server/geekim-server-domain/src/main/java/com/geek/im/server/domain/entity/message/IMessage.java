package com.geek.im.server.domain.entity.message;

import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : IMessage
 * @date : 2024/8/16 20:40
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface IMessage {

    public Map<String, Object> headers();

    public Integer dataType();

    public Integer actionType();

    public Integer featureType();

    public String messageType();

}
