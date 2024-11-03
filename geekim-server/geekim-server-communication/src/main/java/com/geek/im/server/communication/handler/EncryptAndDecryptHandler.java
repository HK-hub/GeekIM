package com.geek.im.server.communication.handler;

import com.alibaba.fastjson2.JSON;
import com.geek.im.server.communication.codec.DecryptHelperDelegate;
import com.geek.im.server.communication.codec.EncryptAndDecryptHelper;
import com.geek.im.server.domain.event.BaseServerEvent;
import com.geek.im.server.domain.property.IMServerProperties;
import com.geek.im.server.infrastructure.channel.CommunicationContext;
import geek.im.server.common.constants.ChannelContextAttributeConstants;
import geek.im.server.common.enums.EncryptAlgorithmEnum;
import geek.im.server.common.enums.ServerEventEnum;
import geek.im.server.common.payload.SecurityPayload;
import geek.im.server.common.payload.text.Base64edMessage;
import geek.im.server.common.payload.text.EncryptedPayload;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.util.AttributeKey;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : HK意境
 * @ClassName : EncryptAndDecryptHandler
 * @date : 2024/8/23 16:53
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class EncryptAndDecryptHandler extends MessageToMessageCodec<Base64edMessage, BaseServerEvent> {

    @Resource
    private DecryptHelperDelegate decryptHelperDelegate;
    @Resource
    private IMServerProperties imServerProperties;


    /**
     * 解密
     *
     * @param context
     * @param base64edMessage
     * @param out
     *
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext context, Base64edMessage base64edMessage, List<Object> out) throws Exception {

        String encryptContent = base64edMessage.getData();
        SecurityPayload securityPayload = JSON.parseObject(encryptContent, SecurityPayload.class);
        // 获取加密算法
        EncryptAlgorithmEnum encryptAlgorithmEnum = securityPayload.getEncryptAlgorithmEnum();

        String securityPayloadData = securityPayload.getPayload();
        String plainPayloadData = null;
        // 是否需要解密
        if (encryptAlgorithmEnum == EncryptAlgorithmEnum.NONE) {
            // 无需解密，序列化为ServerEvent
            plainPayloadData = securityPayloadData;
        } else {
            // 解密
            EncryptAndDecryptHelper decryptHelper = this.decryptHelperDelegate.getDecryptHelper(encryptAlgorithmEnum);
            EncryptedPayload encryptedPayload = JSON.parseObject(encryptContent, EncryptedPayload.class);
            plainPayloadData = decryptHelper.decrypt(encryptedPayload);
        }

        // 设置加密算法
        Channel channel = context.channel();
        CommunicationContext communicationContext = (CommunicationContext) channel.attr(AttributeKey.valueOf(ChannelContextAttributeConstants.communicationContext)).get();
        communicationContext.setEncryptAlgorithmEnum(encryptAlgorithmEnum);

        BaseServerEvent baseServerEvent = JSON.parseObject(plainPayloadData, BaseServerEvent.class);
        baseServerEvent.setServerEventEnum(ServerEventEnum.of(baseServerEvent.getEventType()));
        out.add(baseServerEvent);
    }


    /**
     * 加密
     *
     * @param context
     * @param baseServerEvent
     * @param out:            EncryptPayload
     *
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext context, BaseServerEvent baseServerEvent, List<Object> out) throws Exception {

        // 根据服务端加密算法进行加密
        EncryptAlgorithmEnum encryptAlgorithm = this.imServerProperties.getSecurity().getEncryptAlgorithm();

        // 加密消息
        EncryptAndDecryptHelper encryptAndDecryptHelper = this.decryptHelperDelegate.getDecryptHelper(encryptAlgorithm);
        String encryptString = encryptAndDecryptHelper.encrypt(baseServerEvent);

        EncryptedPayload encryptedPayload = new EncryptedPayload();
        encryptedPayload.setEncryptAlgorithm(encryptAlgorithm.ordinal());
        encryptedPayload.setPayload(encryptString);

        out.add(encryptedPayload);
    }

}
