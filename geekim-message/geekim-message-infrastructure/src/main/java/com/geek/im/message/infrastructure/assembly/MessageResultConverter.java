package com.geek.im.message.infrastructure.assembly;

import com.geek.im.support.domain.dto.MessageSendResult;
import org.apache.rocketmq.client.producer.SendResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author : HK意境
 * @ClassName : MessageResultConverter
 * @date : 2024/3/14 17:06
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Mapper
public interface MessageResultConverter {

    public static final MessageResultConverter INSTANCE = Mappers.getMapper(MessageResultConverter.class);

    /**
     * RocketMQ 发送结果转换为统一发送结果
     *
     * @param sendResult
     *
     * @return
     */
    @Mapping(target = "sendStatus", source = "sendStatus")
    @Mapping(target = "topic", source = "messageQueue.topic")
    @Mapping(target = "brokerName", source = "messageQueue.brokerName")
    @Mapping(target = "queueId", source = "messageQueue.queueId")
    public MessageSendResult convertToResult(SendResult sendResult);


}
