package com.geek.im.server.domain.service;

/**
 * @author : HK意境
 * @ClassName : UserOnlineOfflineService
 * @date : 2024/8/18 22:18
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface UserOnlineOfflineService {

    /**
     * 用户上线
     *
     * @param userId
     *
     * @return
     */
    public boolean online(Long userId);

    /**
     * 用户下线
     *
     * @param userId
     *
     * @return
     */
    public boolean offline(Long userId);

}
