package com.geek.im.server.application.handler;

import com.geek.im.server.domain.event.connect.UserConnectedEvent;
import com.geek.im.server.domain.event.connect.UserDisconnectedEvent;
import com.geek.im.server.domain.service.GroupMemberOnlineService;
import geek.im.group.domain.service.GroupMemberService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author : HK意境
 * @ClassName : UserConnectEventHandler
 * @date : 2024/8/16 20:34
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Component
public class UserConnectEventHandler {

    @Resource
    private GroupMemberOnlineService groupMemberOnlineService;
    @Resource
    private GroupMemberService groupMemberService;

    /**
     * 用户连接上线事件处理：推送通信加解密密钥；会话消息数列表
     *
     * @param connectedOnlineEvent
     */
    @Async
    @EventListener(UserConnectedEvent.class)
    public void onConnectedEvent(UserConnectedEvent connectedOnlineEvent) {

        log.info("用户连接上线成功事件:{}", connectedOnlineEvent);

        // 查询用户加入群聊

    }


    /**
     * 断开连接
     *
     * @param disconnectedEvent
     */
    @Async
    @EventListener(UserDisconnectedEvent.class)
    public void onDisconnected(UserDisconnectedEvent disconnectedEvent) {

        log.info("用户离线事件：{}", disconnectedEvent);


    }


}
