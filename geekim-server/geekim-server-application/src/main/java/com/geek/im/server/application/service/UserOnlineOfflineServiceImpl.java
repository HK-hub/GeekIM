package com.geek.im.server.application.service;

import com.geek.im.server.domain.service.UserOnlineOfflineService;
import geek.im.server.common.constants.UserConnectionConstants;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author : HK意境
 * @ClassName : UserOnlineOfflineServiceImpl
 * @date : 2024/8/18 22:21
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Service
public class UserOnlineOfflineServiceImpl implements UserOnlineOfflineService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 用户在线：缓存结构:zset(排序规则：上线时间)
     *
     * @param userId
     *
     * @return
     */
    @Override
    public boolean online(Long userId) {

        String key = this.buildKey();
        this.redisTemplate.opsForZSet().add(key, userId, System.currentTimeMillis());
        return true;
    }


    @Override
    public boolean offline(Long userId) {

        String key = this.buildKey();
        this.redisTemplate.opsForZSet().remove(key, userId);
        return true;
    }


    private String buildKey() {

        return UserConnectionConstants.userOnlineTableCacheKey;
    }
}
