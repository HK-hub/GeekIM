package com.geek.im.support.domain.enums;

import lombok.Getter;

/**
 * @author : HK意境
 * @ClassName : MessageSendResult
 * @date : 2024/3/9 19:29
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
public enum MessageSendResult {

    SUCCESS(0, "发送成功"),
    FAILED(1, "发送失败"),
    TIMEOUT(4, "发送超时"),
    REJECTED(5, "发送被拒绝"),
    REJECTED_BY_RECIPIENT(6, "发送被拒绝"),
    REJECTED_BY_SENDER(7, "发送被拒绝"),
    REJECTED_BY_SERVER(8, "发送被拒绝"),
    ;

    private final int code;
    private final String desc;

    MessageSendResult(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
