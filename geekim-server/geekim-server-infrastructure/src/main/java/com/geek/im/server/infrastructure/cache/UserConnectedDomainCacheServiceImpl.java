package com.geek.im.server.infrastructure.cache;

import com.geek.im.server.domain.aggregate.UserConnectCacheDomain;
import com.geek.im.server.domain.service.UserConnectedDomainCacheService;
import geek.im.server.common.constants.UserConnectionConstants;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author : HK意境
 * @ClassName : UserConnectedDomainCacheServiceImpl
 * @date : 2024/8/8 14:22
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
public class UserConnectedDomainCacheServiceImpl implements UserConnectedDomainCacheService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取所有用户连接设备信息
     *
     * @return
     */
    @Override
    public Map<Long, List<UserConnectCacheDomain>> getAllConnectedDomain() {

        String keyPattern = UserConnectionConstants.connectionInfosCacheKey;
        Set<String> keySet = this.redisTemplate.keys(keyPattern);
        if (CollectionUtils.isEmpty(keySet)) {
            return Collections.EMPTY_MAP;
        }

        Map<Long, List<UserConnectCacheDomain>> userConnectDeviceListMap = new HashMap<>();
        for (String userConnectDeviceKey : keySet) {
            Long userId = this.parseKey(userConnectDeviceKey);
            // Key<String>: 设备类型， Value<UserConnectCacheDomain.ConnectDevice> 连接信息
            Map<Object, Object> deviceMap = this.redisTemplate.opsForHash().entries(userConnectDeviceKey);

            List<UserConnectCacheDomain> userConnectCacheDomainList = new ArrayList<>();
            for (Map.Entry<Object, Object> deviceEntry : deviceMap.entrySet()) {
                UserConnectCacheDomain.ConnectDevice connectDevice = (UserConnectCacheDomain.ConnectDevice) deviceEntry.getValue();
                UserConnectCacheDomain connectInfo = new UserConnectCacheDomain();
                connectInfo.setUserId(userId).setConnectDevice(connectDevice);
                userConnectCacheDomainList.add(connectInfo);
            }

            userConnectDeviceListMap.put(userId, userConnectCacheDomainList);
        }
        return userConnectDeviceListMap;
    }


    /**
     * 获取指定用户在线设备
     *
     * @param userIds
     *
     * @return
     */
    @Override
    public Map<Long, List<UserConnectCacheDomain>> getConnectedDomainList(Collection<Long> userIds) {

        if (CollectionUtils.isEmpty(userIds)) {
            return Collections.EMPTY_MAP;
        }

        Map<Long, List<UserConnectCacheDomain>> userConnectDeviceListMap = new HashMap<>();
        for (Long userId : userIds) {
            // 查询所有在线设备
            Map<Object, Object> deviceMap = this.redisTemplate.opsForHash().entries(this.buildKey(userId));
            List<UserConnectCacheDomain> userConnectCacheDomainList = new ArrayList<>();
            for (Map.Entry<Object, Object> deviceEntry : deviceMap.entrySet()) {
                UserConnectCacheDomain.ConnectDevice connectDevice = (UserConnectCacheDomain.ConnectDevice) deviceEntry.getValue();
                UserConnectCacheDomain connectInfo = new UserConnectCacheDomain();
                connectInfo.setUserId(userId).setConnectDevice(connectDevice);
                userConnectCacheDomainList.add(connectInfo);
            }
            userConnectDeviceListMap.put(userId, userConnectCacheDomainList);
        }
        return userConnectDeviceListMap;
    }


    /**
     * 保存用户设备连接信息到缓存种
     *
     * @param domain
     *
     * @return
     */
    @Override
    public boolean saveUserConnectDomain(UserConnectCacheDomain domain) {

        Long userId = domain.getUserId();

        String key = this.buildKey(userId);
        UserConnectCacheDomain.ConnectDevice connectDevice = domain.getConnectDevice();
        this.redisTemplate.opsForHash().put(key, connectDevice.getDeviceType(), connectDevice);
        return true;
    }


    /**
     * 获取用户设备连接信息
     *
     * @param userId
     *
     * @return
     */
    @Override
    public List<UserConnectCacheDomain> getUserConnectDomain(Long userId) {

        String key = this.buildKey(userId);

        Set<Object> deviceTypeSet = this.redisTemplate.opsForHash().keys(key);
        if (CollectionUtils.isEmpty(deviceTypeSet)) {
            return Collections.emptyList();
        }

        List<Object> cachedConnectList = this.redisTemplate.opsForHash().multiGet(key, deviceTypeSet);

        List<UserConnectCacheDomain> connectCacheDomainList = new ArrayList<>();
        for (Object connect : cachedConnectList) {
            connectCacheDomainList.add((UserConnectCacheDomain) connect);
        }

        return connectCacheDomainList;
    }


    /**
     * 获取指定设备类型的连接信息
     *
     * @param userId
     * @param deviceType
     *
     * @return
     */
    @Override
    public UserConnectCacheDomain getUserConnectDomain(Long userId, String deviceType) {

        String key = this.buildKey(userId);
        Object connectInfo = this.redisTemplate.opsForHash().get(key, deviceType);
        return (UserConnectCacheDomain) connectInfo;
    }


    /**
     * 移除用户连接设备信息
     *
     * @param userId
     * @param deviceType
     *
     * @return
     */
    @Override
    public boolean removeUserConnectDomain(Long userId, String deviceType) {

        String key = this.buildKey(userId);
        Long deleted = this.redisTemplate.opsForHash().delete(key, deviceType);

        return deleted > 0;
    }


    /**
     * 删除连接设备信息，并且返回设备是否已经全部离线->用户离线
     *
     * @param userId
     * @param deviceType
     *
     * @return status: true：全部离线，false: 还未离线
     */
    @Override
    public boolean removeUserConnectAndGetStatus(Long userId, String deviceType) {

        String key = this.buildKey(userId);
        HashOperations<String, Object, Object> hashOperator = this.redisTemplate.opsForHash();
        Long deleted = hashOperator.delete(key, deviceType);

        Set<Object> keySet = hashOperator.keys(key);
        return CollectionUtils.isEmpty(keySet);
    }


    @Override
    public boolean removeUserConnectDomain(Long userId) {

        String key = this.buildKey(userId);

        HashOperations<String, Object, Object> hashOperator = this.redisTemplate.opsForHash();
        Set<Object> deviceTypeSet = hashOperator.keys(key);
        if (CollectionUtils.isEmpty(deviceTypeSet)) {
            return true;
        }

        Long deleted = hashOperator.delete(key, deviceTypeSet);
        return deleted > 0;
    }


    /**
     * 确认是否还有设备连接
     *
     * @param userId
     *
     * @return true：如果还有设备连接；false: 没有设备连接了
     */
    @Override
    public boolean ensureDeviceConnect(Long userId) {

        String key = this.buildKey(userId);
        Set<Object> keySet = this.redisTemplate.opsForHash().keys(key);
        return CollectionUtils.isEmpty(keySet);
    }


    private String buildKey(Long userId) {

        return UserConnectionConstants.connectionInfosCacheKey + userId;
    }


    private Long parseKey(String key) {

        String userIdString = key.substring(UserConnectionConstants.connectionInfosCacheKey.length() - 1);
        return Long.parseLong(userIdString);
    }

}



