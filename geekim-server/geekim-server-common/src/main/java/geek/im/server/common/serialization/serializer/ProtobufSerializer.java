package geek.im.server.common.serialization.serializer;


import geek.im.server.common.serialization.AbstractSerializer;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import javax.sql.rowset.serial.SerialException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : HK意境
 * @ClassName : ProtobufSerializer
 * @date : 2022/12/28 0:54
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class ProtobufSerializer extends AbstractSerializer {

    private static Map<Class<?>, Schema<?>> cacheSchema = new ConcurrentHashMap();
    private static Objenesis objenesis = new ObjenesisStd(true);


    @Override
    public <T> byte[] serialize(T object) throws SerialException {

        Class<T> clazz = (Class<T>) object.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(clazz);
            return ProtobufIOUtil.toByteArray(object, schema, buffer);
        } catch (Exception e) {
            throw new SerialException(e.getMessage());
        } finally {
            buffer.clear();
        }
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] data) throws SerialException {

        T message = objenesis.newInstance(clazz);
        Schema<T> schema = getSchema(clazz);
        ProtobufIOUtil.mergeFrom(data, message, schema);
        return message;
    }


    @SuppressWarnings("unchecked")
    private static <T> Schema<T> getSchema(Class<T> cls) {
        Schema<T> schema = (Schema<T>) cacheSchema.get(cls);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(cls);
            cacheSchema.put(cls, schema);
        }
        return schema;
    }
}
