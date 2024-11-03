package com.geek.im.server.communication.manager;

import com.geek.im.server.domain.aggregate.UserSecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : HK意境
 * @ClassName : UserSecretKeyManager
 * @date : 2024/8/30 20:56
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
public class UserSecretKeyManager {

    private final ConcurrentHashMap<Long, UserSecretKey> secretKeyMap = new ConcurrentHashMap<>();

}
