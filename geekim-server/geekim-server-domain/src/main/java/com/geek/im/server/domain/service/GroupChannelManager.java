package com.geek.im.server.domain.service;

import io.netty.channel.Channel;

import java.util.Collection;
import java.util.Set;

/**
 * @author : HK意境
 * @ClassName : GroupChannelManager
 * @date : 2024/8/3 21:26
 * @description : 群聊Channel管理器
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface GroupChannelManager extends ChannelManager {

    public Set<Channel> getGroupChannels(Long groupId);

    public Set<Channel> getMembersChannel(Long groupId, Collection<Long> memberIds);

    public Set<Channel> getMemberChannels(Long groupId, Long memberId);

    public boolean containsMemberChannel(Long groupId, Long memberId);

    public boolean removeMemberChannel(Long groupId, Long memberId);

    public boolean removeChannel(Long memberId);

    public boolean clearAll(Long groupId);
}
