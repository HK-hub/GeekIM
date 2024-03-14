package com.geek.im.support.domain.dto;

import com.geek.im.support.domain.enums.MessageSendStatus;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : HK意境
 * @ClassName : MessageSendResult
 * @date : 2024/3/14 17:04
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@Accessors(chain = true)
public class MessageSendResult {

    private MessageSendStatus sendStatus;

    private String msgId;

    private String topic;

    private String brokerName;

    private int queueId;

    private long queueOffset;
    private String transactionId;
    private String offsetMsgId;
    private String regionId;
    private boolean traceOn = true;


    public static MessageSendResult SUCCESS() {

        MessageSendResult result = new MessageSendResult();
        result.setSendStatus(MessageSendStatus.SEND_OK);
        return result;
    }
}
