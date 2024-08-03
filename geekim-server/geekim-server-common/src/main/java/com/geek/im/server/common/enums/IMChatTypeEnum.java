package com.geek.im.server.common.enums;

import lombok.Getter;

/**
 * @author : HK意境
 * @ClassName : IMChatTypeEnum
 * @date : 2024/4/27 20:18
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
public enum IMChatTypeEnum {

    // 会话聊天类型
    PRIVATE_CHAT(1, "私聊"),
    GROUP_CHAT(2, "群聊"),
    ALL_CHAT(3, "全服广播"),
    SYSTEM_CHAT(4, "系统广播"),

    ;

    private final Integer type;
    private final String desc;

    IMChatTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }


}
