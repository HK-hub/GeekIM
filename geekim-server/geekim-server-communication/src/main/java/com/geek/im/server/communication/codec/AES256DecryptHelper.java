package com.geek.im.server.communication.codec;

import com.geek.im.server.domain.event.BaseServerEvent;
import geek.im.server.common.enums.EncryptAlgorithmEnum;
import geek.im.server.common.payload.SecurityPayload;
import geek.im.server.common.payload.text.EncryptedPayload;
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
public class AES256DecryptHelper implements EncryptAndDecryptHelper {

    /**
     * 解密
     *
     * @param securityPayload
     *
     * @return
     */
    @Override
    public String decrypt(SecurityPayload securityPayload) {

        EncryptedPayload encryptedPayload = (EncryptedPayload) securityPayload;
        return encryptedPayload.getPayload();
    }

    @Override
    public EncryptAlgorithmEnum getEncryptAlgorithm() {
        return EncryptAlgorithmEnum.AES256;
    }


    @Override
    public String encrypt(BaseServerEvent baseServerEvent) {
        return null;
    }

}
