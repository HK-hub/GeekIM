package com.geek.im.server.domain.service;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelMatcher;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : HK意境
 * @ClassName : ChannelManager
 * @date : 2024/8/5 17:42
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface ChannelManager {

    static ChannelGroup clientChannelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public default boolean addChannel(Channel channel) {

        clientChannelGroup.add(channel);
        return true;
    }

    public default boolean containsChannel(Channel channel) {

        return clientChannelGroup.contains(channel);
    }

    default Set<Channel> getChannels(ChannelMatcher matcher) {

        if (Objects.isNull(matcher)) {
            return Collections.emptySet();
        }

        return clientChannelGroup.stream().filter(matcher::matches).collect(Collectors.toSet());
    }

    default Channel getChannel(ChannelId id) {

        return clientChannelGroup.find(id);
    }

    default boolean removeChannel(Channel channel) {
        clientChannelGroup.remove(channel);
        return true;
    }

    default boolean clearAll() {
        clientChannelGroup.clear();
        return true;
    }

    default boolean write(ChannelMatcher matcher, Object message) {

        clientChannelGroup.write(message, matcher);
        return true;
    }

    default boolean write(Object message) {

        clientChannelGroup.write(message);
        return true;
    }

    default boolean writeAndFlush(ChannelMatcher matcher, Object message) {
        clientChannelGroup.writeAndFlush(message, matcher);
        return true;
    }

    default boolean writeAndFlush(Object message) {
        clientChannelGroup.writeAndFlush(message);
        return true;
    }

    default boolean flush(ChannelMatcher matcher) {
        clientChannelGroup.flush(matcher);
        return true;
    }

    default boolean flush() {
        clientChannelGroup.flush();
        return true;
    }

}
