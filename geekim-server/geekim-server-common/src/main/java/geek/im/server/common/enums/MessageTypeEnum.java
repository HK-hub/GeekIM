package geek.im.server.common.enums;

import lombok.Getter;

/**
 * @author : HK意境
 * @ClassName : MessageTypeEnum
 * @date : 2024/4/27 20:16
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
public enum MessageTypeEnum {

    // IM服务器收到消息类型
    // 消息类型
    IM_SERVER_RECEIVE_MESSAGE_TYPE_TEXT(1, "文本消息"),
    IM_SERVER_RECEIVE_MESSAGE_TYPE_IMAGE(2, "图片消息"),
    IM_SERVER_RECEIVE_MESSAGE_TYPE_VOICE(3, "语音消息"),
    IM_SERVER_RECEIVE_MESSAGE_TYPE_VIDEO(4, "视频消息"),
    IM_SERVER_RECEIVE_MESSAGE_TYPE_FILE(5, "文件消息"),
    IM_SERVER_RECEIVE_MESSAGE_TYPE_LOCATION(6, "位置消息"),
    IM_SERVER_RECEIVE_MESSAGE_TYPE_CARD(7, "卡片消息"),
    IM_SERVER_RECEIVE_MESSAGE_TYPE_NOTICE(8, "通知消息"),
    IM_SERVER_RECEIVE_MESSAGE_TYPE_RED_PACKET(9, "红包消息"),
    // 代码消息
    IM_SERVER_RECEIVE_MESSAGE_TYPE_CODE(10, "代码消息"),
    // 语音通话
    IM_SERVER_RECEIVE_MESSAGE_TYPE_VOICE_CALL(11, "语音通话"),
    // 视频通话
    IM_SERVER_RECEIVE_MESSAGE_TYPE_VIDEO_CALL(12, "视频通话"),
    // 投票消息
    IM_SERVER_RECEIVE_MESSAGE_TYPE_VOTE(13, "投票消息"),
    // @消息
    IM_SERVER_RECEIVE_MESSAGE_TYPE_AT(14, "@消息"),
    // 回复消息
    IM_SERVER_RECEIVE_MESSAGE_TYPE_REPLY(15, "回复消息"),
    // 撤回消息
    IM_SERVER_RECEIVE_MESSAGE_TYPE_REVOKE(16, "撤回消息"),
    // 控制消息:
    IM_SERVER_RECEIVE_MESSAGE_TYPE_CONTROL(17, "控制消息"),
    // 多媒体消息
    IM_SERVER_RECEIVE_MESSAGE_TYPE_MULTIMEDIA(18, "多媒体消息"),
    // 链接消息
    IM_SERVER_RECEIVE_MESSAGE_TYPE_LINK(19, "链接消息"),
    // 聊天记录消息
    IM_SERVER_RECEIVE_MESSAGE_TYPE_CHAT_RECORD(20, "聊天记录消息"),

    ;


    private final Integer code;

    private final String desc;

    MessageTypeEnum(int code, String desc) {

        this.code = code;
        this.desc = desc;
    }
}

