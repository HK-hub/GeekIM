package com.geek.im.server.communication.handler;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.geek.im.server.domain.property.IMServerProperties;
import com.geek.im.server.infrastructure.util.SpringUtils;
import geek.im.server.common.payload.text.Base64edMessage;
import geek.im.server.common.payload.text.EncryptedPayload;
import geek.im.server.common.util.Base64Util;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author : HK意境
 * @ClassName : Base64CodecHandler
 * @date : 2024/8/23 16:39
 * @description : 取出Content内容
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Data
@Component
@ChannelHandler.Sharable
public class Base64CodecHandler extends MessageToMessageCodec<TextWebSocketFrame, EncryptedPayload> {

    @Resource
    private IMServerProperties imServerProperties;

    public Base64CodecHandler() {
        this.imServerProperties = SpringUtils.getBean(IMServerProperties.class);
    }

    public Base64CodecHandler(@Autowired IMServerProperties imServerProperties) {
        this.imServerProperties = imServerProperties;
    }

    /**
     * 对通过WebSocket协议获取到的text数据进行解码，解密
     *
     * @param context
     * @param textWebSocketFrame 包裹结构为：ServerEvent
     * @param out
     *
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext context, TextWebSocketFrame textWebSocketFrame, List<Object> out) throws Exception {

        String base64edText = textWebSocketFrame.text();

        try {
            Base64edMessage base64edMessage = JSON.parseObject(base64edText, Base64edMessage.class);
            // 是否进行了base64编码
            if (Objects.nonNull(base64edMessage) && BooleanUtils.isTrue(base64edMessage.getEncoded())) {
                // 进行Base64解码
                String realData = Base64Util.decode(base64edMessage.getData());
                base64edMessage.setData(realData);
            }
            out.add(base64edMessage);
        } catch (Exception e) {
            log.error("textWebSocketFrame 数据解码，序列化异常：{}", base64edText);
            // 响应错误
            context.writeAndFlush(new TextWebSocketFrame("消息解码失败!"));
        }
    }


    /**
     * 编码
     *
     * @param context
     * @param payload
     * @param out     {@link TextWebSocketFrame}
     *
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext context, EncryptedPayload payload, List<Object> out) throws Exception {

        Base64edMessage message = new Base64edMessage();

        Boolean base64ed = this.imServerProperties.getSecurity().getBase64ed();
        if (BooleanUtils.isFalse(base64ed)) {
            message.setEncoded(false);
            message.setData(JSON.toJSONString(payload));
        } else {
            // 进行编码
            message.setEncoded(true);
            String encodedData = Base64Util.encode(JSON.toJSONBytes(payload));
            message.setData(encodedData);
        }

        String text = JSON.toJSONString(message, JSONWriter.Feature.WriteMapNullValue);
        TextWebSocketFrame frame = new TextWebSocketFrame(text);
        out.add(frame);
    }
}
