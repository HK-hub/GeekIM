package geek.im.server.common.exception;

import com.geek.im.common.exception.CommonException;
import lombok.Data;

/**
 * @author : HK意境
 * @ClassName : IMServerException
 * @date : 2024/8/2 10:01
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
public class IMServerException extends CommonException {


    public IMServerException(String message) {
        super(message);
    }
}
