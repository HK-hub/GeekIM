package geek.im.server.common.payload.text;

import geek.im.server.common.payload.SecurityPayload;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : HK意境
 * @ClassName : EncryptedPayload
 * @date : 2024/8/21 20:31
 * @description : 采用 Base64 编码加密后的消息内容和 IV
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
@Setter
public class EncryptedPayload extends SecurityPayload {

    /**
     * Initialization Vector初始化向量：
     * IV 是一个与加密密钥配合使用的随机数或固定值，主要有以下作用：
     * - 防止模式分析：如果对相同的明文使用相同的加密密钥和不变的加密过程，输出的密文也是相同的。IV的引入可以使得即使相同的明文在相同的密钥下加密，也会得到不同的密文，增加了破解难度。
     * - 确保随机性：通过引入随机性，IV使得相同的明文在每次加密时都产生不同的密文，即使使用的是相同的密钥。
     */
    protected String iv;

    /**
     * 消息认证码MAC：为防止消息被篡改，可以使用HMAC（基于哈希的消息认证码），结合SHA-256等哈希算法生成消息的校验码。HMAC可以保证消息的完整性。
     * 完整性保护：使用 HMAC-SHA256 生成消息的 HMAC，防止消息在传输过程中被篡改。
     */
    protected String hmac;
}
