package com.geek.im.server.domain.service;

import java.util.Set;

/**
 * @author : HK意境
 * @ClassName : GroupMemberOnlineService
 * @date : 2024/8/19 10:10
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface GroupMemberOnlineService {

    public boolean addOnlineMember(Long groupId, Long userId);

    public boolean offlineMember(Long groupId, Long userId);

    public boolean memberIsOnline(Long groupId, Long userId);

    public Set<Long> getGroupOnlineMember(Long groupId);
}
