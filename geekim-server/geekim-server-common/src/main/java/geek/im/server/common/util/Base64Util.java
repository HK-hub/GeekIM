package geek.im.server.common.util;

import org.apache.commons.codec.binary.Base64;

/**
 * Base64 编解码工具类
 */
public class Base64Util {

    /**
     * base64 编码
     *
     * @param bytes
     *
     * @return
     */
    public static String encode(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }


    /**
     * base64 解码
     *
     * @param bytes
     *
     * @return
     */
    public static String decodeAsString(byte[] bytes) {
        return new String(Base64.decodeBase64(bytes));
    }

    /**
     * 解码
     *
     * @param data
     *
     * @return
     */
    public static String decode(String data) {

        return new String(Base64.decodeBase64(data));
    }

    public static byte[] decodeToBytes(String data) {

        return Base64.decodeBase64(data);
    }


    /**
     * base64 解码
     *
     * @param bytes
     *
     * @return
     */
    public static byte[] decode(byte[] bytes) {
        return Base64.decodeBase64(bytes);
    }
}
