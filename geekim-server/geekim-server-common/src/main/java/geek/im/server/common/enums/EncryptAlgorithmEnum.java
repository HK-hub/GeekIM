package geek.im.server.common.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : HK意境
 * @ClassName : EncryptAlgorithmEnum
 * @date : 2024/8/22 18:53
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
public enum EncryptAlgorithmEnum {

    NONE(),
    AES256(),
    ;

    private static final Map<Integer, EncryptAlgorithmEnum> algorithmEnumMap = new HashMap<>();

    static {
        for (EncryptAlgorithmEnum algorithmEnum : values()) {
            algorithmEnumMap.put(algorithmEnum.ordinal(), algorithmEnum);
        }
    }

    public static EncryptAlgorithmEnum of(Integer type) {

        return algorithmEnumMap.get(type);
    }

}
