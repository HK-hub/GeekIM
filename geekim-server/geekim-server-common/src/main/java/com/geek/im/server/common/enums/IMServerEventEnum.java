package com.geek.im.server.common.enums;

import lombok.Getter;

/**
 * @author : HK意境
 * @ClassName : IMServerEventEnum
 * @date : 2024/4/27 20:15
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
public enum IMServerEventEnum {

    CONNECTION(1, "连接"),
    HEARTBEAT(2, "心跳"),
    MESSAGE(3, "消息"),
    TRANSFER(5, "文件传输"),
    ;

    //
    // 心跳，登录，注册，群聊，私聊，文件传输，图片传输，语音传输，视频传输，文件下载，文件上传，文件分享，文件下载，文件上传，文件分享，文件下载，文件上传，文件分享，文件下载，文件上传，文件分享，文件下载，文件上传，文件分享，文件下载，文件上传，文件分享，文件下载，文件上传，文件分享，文件下载，文件上传，文件分享


    private final Integer code;

    private final String desc;


    IMServerEventEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
