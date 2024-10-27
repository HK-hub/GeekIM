package geek.im.server.common.protocol;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : MessageFrame
 * @date : 2024/8/16 20:54
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@Accessors(chain = true)
public class MessageFrame {

    /**
     * 序列化协议
     */
    private String serialization;

    /**
     * 消息版本
     */
    private Integer version;

    /**
     * 魔数
     */
    private Integer magic;


    /**
     * 数据长度
     */
    private Integer length;

    /**
     * 消息头
     */
    protected Map<String, Object> headers;

    /**
     * 校验码
     */
    protected String verifyMask;

    /**
     * 动作类型
     */
    protected Integer dataType;

    /**
     * 数据动作类型
     */
    protected Integer actionType;

    /**
     * 特征类型
     */
    protected Integer featureType;

    /**
     * 消息类型
     */
    protected String messageType;

    /**
     * 负载数据
     */
    protected String data;
}
