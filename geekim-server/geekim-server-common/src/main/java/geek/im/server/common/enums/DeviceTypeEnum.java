package geek.im.server.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

/**
 * @author : HK意境
 * @ClassName : DeviceTypeEnum
 * @date : 2024/8/7 21:06
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DeviceTypeEnum {

    PC("PC"),
    APP("APP"),
    H5("H5"),
    Mini("Mini"),
    Web("Web"),
    UnKnown("Unknown"),
    ;

    private final String code;

    DeviceTypeEnum(String code) {
        this.code = code;
    }
}
