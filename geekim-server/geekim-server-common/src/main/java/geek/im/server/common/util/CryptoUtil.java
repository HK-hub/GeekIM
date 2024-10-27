package geek.im.server.common.util;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

/**
 * @author : HK意境
 * @ClassName : CryptoUtil
 * @date : 2024/8/30 21:04
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class CryptoUtil {

    private static final int defaultAESLength = 256;

    private static final String defaultKeyType = "AES";


    public static SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(defaultKeyType);
        keyGen.init(defaultAESLength);
        return keyGen.generateKey();
    }

    public static SecretKey generateAESKey(int keySize) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(defaultKeyType);
        keyGen.init(keySize);
        return keyGen.generateKey();
    }

}
