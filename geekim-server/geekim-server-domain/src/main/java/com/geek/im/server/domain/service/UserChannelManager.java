package com.geek.im.server.domain.service;

import io.netty.channel.Channel;

import java.util.Set;

/**
 * @author : HK意境
 * @ClassName : UserChannelManager
 * @date : 2024/8/3 21:26
 * @description : 用户Channel管理器
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface UserChannelManager extends ChannelManager {

    /**
     * 获取用户channel集合：适用于允许多端登录，多设备登录场景
     *
     * @param userId
     *
     * @return
     */
    public Set<Channel> getUserChannels(Long userId);

    /**
     * 获取用户channel：适用于不支持多端登录，不支持多设备登录的场景
     *
     * @param userId
     *
     * @return
     */
    public Channel getUserChannel(Long userId);

    public boolean addChannel(Long userId, Channel channel);

    public boolean containsChannel(Long userId);

    public boolean containsChannel(Long userId, Channel channel);

    public boolean removeChannel(Long userId, Channel channel);

    /**
     * 清除指定用户所有Channel
     *
     * @param userId
     *
     * @return
     */
    public boolean clearAll(Long userId);

    @Override
    boolean addChannel(Channel channel);

    @Override
    boolean removeChannel(Channel channel);

    @Override
    boolean clearAll();

    public boolean write(Long userId, Object message);

    boolean writeAndFlush(Long userId, Object message);


    boolean writeAndFlush(Channel channel, Object message);

    boolean flush(Long userId);

}
