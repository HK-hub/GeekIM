package geek.im.server.common.payload;

import geek.im.server.common.enums.EncryptAlgorithmEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : HK意境
 * @ClassName : SecurityPayload
 * @date : 2024/8/22 21:38
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
@Setter
public abstract class SecurityPayload {

    protected String payload;

    /**
     * 负载数据加密算法：{@link geek.im.server.common.enums.EncryptAlgorithmEnum}
     */
    protected Integer encryptAlgorithm;

    public EncryptAlgorithmEnum getEncryptAlgorithmEnum() {
        return EncryptAlgorithmEnum.of(this.encryptAlgorithm);
    }

}
