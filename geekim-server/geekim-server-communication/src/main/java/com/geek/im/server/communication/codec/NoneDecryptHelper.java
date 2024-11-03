package com.geek.im.server.communication.codec;

import com.alibaba.fastjson2.JSON;
import com.geek.im.server.domain.event.BaseServerEvent;
import geek.im.server.common.enums.EncryptAlgorithmEnum;
import geek.im.server.common.payload.SecurityPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : HK意境
 * @ClassName : EncryptAndDecryptHelper
 * @date : 2024/10/27 15:22
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
public class NoneDecryptHelper implements EncryptAndDecryptHelper {

    /**
     * 解密
     *
     * @param payload
     *
     * @return
     */
    @Override
    public String decrypt(SecurityPayload payload) {

        return payload.getPayload();
    }

    @Override
    public EncryptAlgorithmEnum getEncryptAlgorithm() {
        return EncryptAlgorithmEnum.NONE;
    }


    @Override
    public String encrypt(BaseServerEvent baseServerEvent) {

        return JSON.toJSONString(baseServerEvent);
    }

}
