package geek.im.server.common.serialization.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import geek.im.server.common.serialization.AbstractSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

/**
 * @author : HK意境
 * @ClassName : XmlSerializer
 * @date : 2022/12/28 0:54
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Setter
@Getter
public class XmlSerializer extends AbstractSerializer {

    private ObjectMapper objectMapper = new XmlMapper();

    public XmlSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> byte[] serialize(T object) throws SerialException {

        byte[] bytes;
        try {
            bytes = objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new SerialException(e.getMessage());
        }
        return bytes;
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] data) throws SerialException {

        T message = null;
        try {
            message = this.objectMapper.readValue(data, clazz);
        } catch (IOException e) {
            throw new SerialException(e.getMessage());
        }

        return message;
    }
}
