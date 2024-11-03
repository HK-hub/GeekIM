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

    public boolean addGroupChannel(Long groupId, Long memberId, Channel channel);

    public boolean write(Long groupId, Object message);

    public boolean write(Long groupId, Long memberId, Object message);

    public boolean writeAndFlush(Long groupId, Object message);

    public boolean writeAndFlush(Long groupId, Long memberId, Object message);

    public boolean flush(Long groupId, Object message);

    public boolean flush(Long groupId, Long memberId, Object message);

    public boolean removeMemberChannel(Long groupId, Long memberId);

    public boolean removeUserChannel(Long memberId);

    public boolean clearAll(Long groupId);
}
