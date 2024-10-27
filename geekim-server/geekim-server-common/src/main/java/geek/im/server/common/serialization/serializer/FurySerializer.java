package geek.im.server.common.serialization.serializer;

import geek.im.server.common.serialization.AbstractSerializer;
import org.apache.fury.Fury;
import org.apache.fury.config.Language;

import javax.sql.rowset.serial.SerialException;

/**
 * @author : HK意境
 * @ClassName : FurySerializer
 * @date : 2024/8/21 20:01
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class FurySerializer extends AbstractSerializer {

    private final static Fury fury;

    static {
        fury = Fury.builder().withLanguage(Language.JAVA)
                .requireClassRegistration(true)
                .withRefTracking(true)
                .build();
    }


    /**
     * 序列化
     *
     * @param object
     *
     * @return
     *
     * @throws SerialException
     */
    @Override
    public <T> byte[] serialize(T object) throws SerialException {

        fury.register(object.getClass());
        return fury.serialize(object);
    }

    /**
     * 反序列化
     *
     * @param clazz
     * @param data
     *
     * @return
     *
     * @throws SerialException
     */
    @Override
    public <T> T deserialize(Class<T> clazz, byte[] data) throws SerialException {

        T object = fury.deserializeJavaObject(data, clazz);
        return object;
    }
}
