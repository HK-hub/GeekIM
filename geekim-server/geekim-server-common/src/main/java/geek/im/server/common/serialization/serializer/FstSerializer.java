package geek.im.server.common.serialization.serializer;

import geek.im.server.common.serialization.AbstractSerializer;
import org.nustaq.serialization.FSTConfiguration;

import javax.sql.rowset.serial.SerialException;

/**
 * @author : HK意境
 * @ClassName : FstSerializer
 * @date : 2024/8/21 19:38
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class FstSerializer extends AbstractSerializer {


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

        FSTConfiguration configuration = FSTConfiguration.createAndroidDefaultConfiguration();
        return configuration.asByteArray(object);
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

        FSTConfiguration configuration = FSTConfiguration.createAndroidDefaultConfiguration();
        T object = (T) configuration.asObject(data);
        return object;
    }
}
