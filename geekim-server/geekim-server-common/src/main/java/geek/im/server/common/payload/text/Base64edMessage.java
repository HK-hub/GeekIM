package geek.im.server.common.payload.text;

import lombok.Data;

/**
 * @author : HK意境
 * @ClassName : Base64edMessage
 * @date : 2024/10/27 13:47
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
public class Base64edMessage {

    /**
     * 是否base64编码后的
     */
    private Boolean encoded = false;

    /**
     * 数据载体
     */
    private String data;
}
