package com.geek.im.support.domain.callback;

import com.geek.im.support.domain.dto.MessageSendResult;

/**
 * @author : HK意境
 * @ClassName : MessageSendCallback
 * @date : 2024/3/14 18:59
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface MessageSendCallback {

    public void onSuccess(MessageSendResult sendResult);

    public void onException(Throwable exception);

}
