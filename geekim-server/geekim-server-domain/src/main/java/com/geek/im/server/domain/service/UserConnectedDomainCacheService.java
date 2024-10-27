package com.geek.im.server.domain.service;

import com.geek.im.server.domain.aggregate.UserConnectCacheDomain;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : UserConnectedDomainCacheService
 * @date : 2024/8/5 17:10
 * @description : 缓存Channel通道服务
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface UserConnectedDomainCacheService {

    public Map<Long, List<UserConnectCacheDomain>> getAllConnectedDomain();

    public Map<Long, List<UserConnectCacheDomain>> getConnectedDomainList(Collection<Long> userIds);

    public boolean saveUserConnectDomain(UserConnectCacheDomain domain);

    public List<UserConnectCacheDomain> getUserConnectDomain(Long userId);

    public UserConnectCacheDomain getUserConnectDomain(Long userId, String deviceType);

    public boolean removeUserConnectDomain(Long userId, String deviceType);

    /**
     * 删除连接设备信息，并且返回设备是否已经全部离线->用户离线
     *
     * @param userId
     * @param deviceType
     *
     * @return
     */
    public boolean removeUserConnectAndGetStatus(Long userId, String deviceType);

    public boolean removeUserConnectDomain(Long userId);

    public boolean ensureDeviceConnect(Long userId);
}
