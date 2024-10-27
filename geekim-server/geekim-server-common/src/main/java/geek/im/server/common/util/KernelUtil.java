package geek.im.server.common.util;

/**
 * @author : HK意境
 * @ClassName : KernelUtil
 * @date : 2024/4/26 23:03
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class KernelUtil {

    public static int getAvailableCores() {
        return Runtime.getRuntime().availableProcessors();
    }


}
