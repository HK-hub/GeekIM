package com.geek.im.server.application.service;

import com.geek.im.server.domain.service.GroupMemberOnlineService;
import geek.im.server.common.constants.GroupConstants;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : HK意境
 * @ClassName : GroupMemberOnlineServiceImpl
 * @date : 2024/8/19 10:15
 * @description : 群成员在线表: ZSet<Long> ，按照在线时间排序
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Service
public class GroupMemberOnlineServiceImpl implements GroupMemberOnlineService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public boolean addOnlineMember(Long groupId, Long userId) {

        String key = this.buildKey(groupId);
        SetOperations<String, Object> setOperator = this.redisTemplate.opsForSet();
        Boolean member = setOperator.isMember(key, userId);
        if (BooleanUtils.isFalse(member)) {
            setOperator.add(key, userId);
        }
        return true;
    }

    @Override
    public boolean offlineMember(Long groupId, Long userId) {

        String key = this.buildKey(groupId);
        this.redisTemplate.opsForSet().remove(key, userId);
        return true;
    }

    @Override
    public boolean memberIsOnline(Long groupId, Long userId) {

        String key = this.buildKey(groupId);
        Boolean member = this.redisTemplate.opsForSet().isMember(key, groupId);
        return BooleanUtils.isTrue(member);

    }


    @Override
    public Set<Long> getGroupOnlineMember(Long groupId) {

        String key = this.buildKey(groupId);
        Set<Object> members = this.redisTemplate.opsForSet().members(key);
        if (CollectionUtils.isEmpty(members)) {
            return Collections.emptySet();
        }

        Set<Long> memberIdSet = members.stream().map(member -> (Long) member).collect(Collectors.toSet());
        return memberIdSet;
    }


    private String buildKey(Long groupId) {

        return GroupConstants.onlineGroupMemberCacheKey + groupId;
    }
}
