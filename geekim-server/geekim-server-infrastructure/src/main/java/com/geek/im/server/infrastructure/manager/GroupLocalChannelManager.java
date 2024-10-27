package com.geek.im.server.infrastructure.manager;

import com.geek.im.server.domain.service.GroupChannelManager;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : HK意境
 * @ClassName : GroupLocalChannelManager
 * @date : 2024/8/5 17:08
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
public class GroupLocalChannelManager implements GroupChannelManager {

    /**
     * 保存用户与其对应的channel: 使用 <set>集合是为了支持多端登录, 如果为群聊，<set> 集合则表示群员的 Channel
     */
    public static final Map<Long, Set<Channel>> groupChannelMap = new ConcurrentHashMap<>(1024);

    /**
     * 获取指定群聊本地Channel集合
     *
     * @param groupId
     *
     * @return
     */
    @Override
    public Set<Channel> getGroupChannels(Long groupId) {


        return null;
    }

    @Override
    public Set<Channel> getMembersChannel(Long groupId, Collection<Long> memberIds) {
        return null;
    }

    @Override
    public Set<Channel> getMemberChannels(Long groupId, Long memberId) {
        return null;
    }

    @Override
    public boolean containsMemberChannel(Long groupId, Long memberId) {
        return false;
    }

    @Override
    public boolean removeMemberChannel(Long groupId, Long memberId) {
        return false;
    }

    @Override
    public boolean removeChannel(Long memberId) {
        return false;
    }

    @Override
    public boolean clearAll(Long groupId) {
        return false;
    }
}
