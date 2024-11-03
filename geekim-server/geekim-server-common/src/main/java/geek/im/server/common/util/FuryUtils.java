package geek.im.server.common.util;

import org.apache.fury.Fury;
import org.apache.fury.ThreadSafeFury;
import org.apache.fury.config.CompatibleMode;
import org.apache.fury.config.Language;

/**
 * @author : HK意境
 * @ClassName : FuryUtils
 * @date : 2024/11/3 20:17
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class FuryUtils {

    // 重用fury
    private static final ThreadSafeFury fury;


    static {

        fury = Fury.builder()
                .withLanguage(Language.JAVA)
                // 启用引用跟踪以支持共享/循环引用。
                // 如果没有重复引用，禁用它可以提高性能。
                .withRefTracking(false)
                // 压缩 int 类型以获得更小的尺寸
                // .withIntCompressed(true)
                // 压缩 long 类型以获得更小的尺寸
                // .withLongCompressed(true)
                .withCompatibleMode(CompatibleMode.SCHEMA_CONSISTENT)
                // 启用异步多线程编译
                .withAsyncCompilation(true)
                .buildThreadSafeFury();
    }


    public static byte[] serialize(Object object) {

        return fury.serialize(object);
    }


    public static <T> T deserialize(byte[] data, Class<T> clazz) {

        return fury.deserializeJavaObject(data, clazz);
    }

}
