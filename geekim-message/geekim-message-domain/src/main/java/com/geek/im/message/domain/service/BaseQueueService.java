package com.geek.im.message.domain.service;

/**
 * @InterfaceName BaseQueueService
 * @Description 队列基础服务类
 * @DATE 2022/4/21 14:35
 **/
public interface BaseQueueService {

    /**
     * 描述: 从Redis获取消息配置信息
     *
     * @param key
     *
     * @return com.zm.msg.model.MsgConfig
     *
     * @author wangke
     * @date 2022/4/21 14:38
     */
    // MsgConfig getMsgConfigByKey(String key);

    /**
     * 描述: 替换模板中的动态字段，返回组装好的消息内容
     *
     * @param msg 消息模板
     * @param obj 模板消息数据实体
     *
     * @return
     *
     * @throws Exception 反射获取get,set方法失败
     * @author wangke
     * @date 2022/4/21 14:55
     */
    String buildContent(String msg, Object obj) throws Exception;

}
