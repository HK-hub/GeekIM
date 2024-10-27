package com.geek.im.server.infrastructure.manager;

import com.geek.im.server.domain.service.UserChannelManager;
import geek.im.server.common.constants.ChannelContextAttributeConstants;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : HK意境
 * @ClassName : UserLocalChannelManager
 * @date : 2024/8/5 17:08
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
public class UserLocalChannelManager implements UserChannelManager {

    /**
     * 保存用户与其对应的channel: 使用 <set>集合是为了支持多端登录, 如果为群聊，<set> 集合则表示群员的 Channel
     */
    public static final Map<Long, Set<Channel>> userChannelMap = new ConcurrentHashMap<>(1024);


    /**
     * 获取用户channel集合：适用于允许多端登录，多设备登录场景
     *
     * @param userId
     *
     * @return
     */
    @Override
    public Set<Channel> getUserChannels(Long userId) {
        return userChannelMap.get(userId);
    }


    /**
     * 获取用户channel：适用于不支持多端登录，不支持多设备登录的场景
     *
     * @param userId
     *
     * @return
     */
    @Override
    public Channel getUserChannel(Long userId) {

        Set<Channel> channels = userChannelMap.get(userId);
        if (CollectionUtils.isEmpty(channels)) {
            return null;
        }
        return channels.stream().findFirst().orElse(null);
    }


    @Override
    public boolean addChannel(Channel channel) {

        Long userId = (Long) channel.attr(AttributeKey.valueOf(ChannelContextAttributeConstants.userIdAttribute)).get();
        return this.addChannel(userId, channel);
    }


    /**
     * 添加Channel
     *
     * @param userId
     * @param channel
     *
     * @return
     */
    @Override
    public boolean addChannel(Long userId, Channel channel) {

        Set<Channel> channels = userChannelMap.getOrDefault(userId, new HashSet<>());

        channels.add(channel);
        userChannelMap.put(userId, channels);
        clientChannelGroup.add(channel);
        return true;
    }

    @Override
    public boolean containsChannel(Long userId) {
        return userChannelMap.containsKey(userId);
    }

    @Override
    public boolean containsChannel(Long userId, Channel channel) {

        Set<Channel> channelSet = userChannelMap.getOrDefault(userId, new HashSet<>());
        return channelSet.contains(channel) && clientChannelGroup.remove(channel);
    }

    @Override
    public boolean removeChannel(Long userId, Channel channel) {

        if (Objects.isNull(channel)) {
            return true;
        }

        // 释放连接
        channel.disconnect();
        if (Objects.nonNull(userId)) {
            Set<Channel> channels = userChannelMap.get(userId);
            if (Objects.nonNull(channels)) {
                channels.remove(channel);
            }
        }

        clientChannelGroup.remove(channel);
        return true;
    }

    @Override
    public boolean removeChannel(Channel channel) {

        Long userId = (Long) channel.attr(AttributeKey.valueOf(ChannelContextAttributeConstants.userIdAttribute)).get();
        return removeChannel(userId, channel);
    }

    @Override
    public boolean clearAll() {

        // 释放连接
        clientChannelGroup.disconnect();
        clientChannelGroup.clear();

        userChannelMap.clear();
        return true;
    }

    @Override
    public boolean write(Long userId, Object message) {

        Set<Channel> channelSet = userChannelMap.getOrDefault(userId, Collections.emptySet());
        for (Channel channel : channelSet) {
            channel.write(message);
        }
        return true;
    }


    @Override
    public boolean writeAndFlush(Channel channel, Object message) {

        channel.writeAndFlush(message);
        return true;
    }


    @Override
    public boolean flush(Long userId) {

        Set<Channel> channelSet = userChannelMap.getOrDefault(userId, Collections.emptySet());
        for (Channel channel : channelSet) {
            channel.flush();
        }

        return true;
    }

    @Override
    public boolean writeAndFlush(Long userId, Object message) {

        Set<Channel> channelSet = userChannelMap.getOrDefault(userId, Collections.emptySet());
        for (Channel channel : channelSet) {
            channel.writeAndFlush(message);
        }
        return true;
    }


    /**
     * 清除指定用户所有Channel
     *
     * @param userId
     *
     * @return
     */
    @Override
    public boolean clearAll(Long userId) {

        Set<Channel> channelSet = userChannelMap.getOrDefault(userId, Collections.emptySet());
        for (Channel channel : channelSet) {
            // 释放连接
            channel.disconnect();
            clientChannelGroup.remove(channel);
        }

        return true;
    }


}
