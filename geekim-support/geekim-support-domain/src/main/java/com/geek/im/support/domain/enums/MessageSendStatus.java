package com.geek.im.support.domain.enums;

import lombok.Getter;

/**
 * @author : HK意境
 * @ClassName : MessageSendStatus
 * @date : 2024/3/9 19:29
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
public enum MessageSendStatus {

    // 发送成功
    SEND_OK,
    // 刷盘超时
    FLUSH_DISK_TIMEOUT,
    // 从节点刷盘超时
    FLUSH_SLAVE_TIMEOUT,
    // 无可用节点
    SLAVE_NOT_AVAILABLE,
    // 拒绝
    REJECTED,
    // 失败
    FAILED,
    ;

}
