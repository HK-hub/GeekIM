package geek.im.server.common.serialization;

import lombok.NonNull;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

/**
 * @author : HK意境
 * @ClassName : SerializerFacade
 * @date : 2022/12/28 0:57
 * @description : 门面模式
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class SerializerFacade {

    /**
     * 序列化
     *
     * @param serializer
     * @param object
     * @param <T>
     *
     * @return
     *
     * @throws SerialException
     */
    public static <T> byte[] serialize(@NonNull SerializationEnum serializer, T object) throws SerialException, IOException {
        // 序列化数据
        return serializer.serialize(object);
    }


    /**
     * 返序列化
     *
     * @param serializer
     * @param clazz
     * @param data
     * @param <T>
     *
     * @return
     *
     * @throws SerialException
     */
    public static <T> T deserialize(@NonNull SerializationEnum serializer, Class<T> clazz, byte[] data) throws SerialException {
        // 反序列化数据
        return serializer.deserialize(clazz, data);
    }

}
