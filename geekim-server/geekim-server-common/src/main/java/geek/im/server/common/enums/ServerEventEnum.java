package geek.im.server.common.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : ServerEventEnum
 * @date : 2024/4/27 20:15
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
public enum ServerEventEnum {

    UNKNOWN(0, "未知"),
    HEARTBEAT(2, "心跳"),
    MESSAGE(3, "消息"),
    TRANSFER(4, "文件传输"),
    CONTROL(5, "控制"),
    NOTIFICATION(6, "通知"),
    SYSTEM(7, "系统"),
    ;

    //
    // 心跳，登录，注册，群聊，私聊，文件传输，图片传输，语音传输，视频传输，文件下载，文件上传，文件分享，文件下载，文件上传，文件分享，文件下载，文件上传，文件分享，文件下载，文件上传，文件分享，文件下载，文件上传，文件分享，文件下载，文件上传，文件分享，文件下载，文件上传，文件分享，文件下载，文件上传，文件分享


    private final Integer code;

    private final String desc;

    private static final Map<Integer, ServerEventEnum> eventTypeMap = new HashMap<>();

    static {

        for (ServerEventEnum eventEnum : values()) {
            eventTypeMap.put(eventEnum.getCode(), eventEnum);
        }
    }


    ServerEventEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public static ServerEventEnum of(Integer code) {

        return eventTypeMap.getOrDefault(code, UNKNOWN);
    }
}
