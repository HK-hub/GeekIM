package com.geek.im.server.communication.codec;

import com.geek.im.server.domain.event.BaseServerEvent;
import geek.im.server.common.enums.EncryptAlgorithmEnum;
import geek.im.server.common.payload.SecurityPayload;

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
public interface EncryptAndDecryptHelper {

    public EncryptAlgorithmEnum getEncryptAlgorithm();

    public String encrypt(BaseServerEvent baseServerEvent);

    public String decrypt(SecurityPayload payload);

}
