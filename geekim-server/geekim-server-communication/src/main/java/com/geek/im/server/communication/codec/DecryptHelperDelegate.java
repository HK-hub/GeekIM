package com.geek.im.server.communication.codec;

import geek.im.server.common.enums.EncryptAlgorithmEnum;
import geek.im.server.common.payload.SecurityPayload;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : DecryptHelperDelegate
 * @date : 2024/10/27 15:30
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
public class DecryptHelperDelegate implements ApplicationRunner {

    @Resource
    private List<EncryptAndDecryptHelper> decryptHelperList;

    private static final Map<EncryptAlgorithmEnum, EncryptAndDecryptHelper> decryptHelperMap = new HashMap<>();

    /**
     * 获取解密器
     *
     * @param algorithmEnum
     *
     * @return
     */
    public EncryptAndDecryptHelper getDecryptHelper(EncryptAlgorithmEnum algorithmEnum) {
        return decryptHelperMap.get(algorithmEnum);
    }


    /**
     * 解密
     *
     * @param algorithmEnum
     * @param securityPayload
     *
     * @return
     */
    public String decrypt(EncryptAlgorithmEnum algorithmEnum, SecurityPayload securityPayload) {

        EncryptAndDecryptHelper decryptHelper = this.getDecryptHelper(algorithmEnum);
        return decryptHelper.decrypt(securityPayload);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (CollectionUtils.isNotEmpty(decryptHelperList)) {
            for (EncryptAndDecryptHelper decryptHelper : decryptHelperList) {
                decryptHelperMap.put(decryptHelper.getEncryptAlgorithm(), decryptHelper);
            }
        }
    }
}
