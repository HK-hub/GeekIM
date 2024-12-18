package geek.im.server.common.serialization;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import geek.im.server.common.serialization.serializer.KryoSerializer;
import geek.im.server.common.util.FuryUtils;
import geek.im.server.common.util.ProtoStuffUtils;
import lombok.Getter;

import javax.sql.rowset.serial.SerialException;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author : HK意境
 * @ClassName : SerializationEnum
 * @date : 2022/12/28 0:27
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
public enum SerializationEnum implements Serializable {

    // fury序列化
    Fury("fury") {
        @Override
        public <T> byte[] serialize(T object) throws SerialException {
            return FuryUtils.serialize(object);
        }

        @Override
        public <T> T deserialize(Class<T> clazz, byte[] data) throws SerialException {
            return FuryUtils.deserialize(data, clazz);
        }
    },


    // jdk序列化
    Java("java") {
        @Override
        public <T> byte[] serialize(T object) throws SerialException {
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(bos)) {

                // 写入对象
                oos.writeObject(object);
                byte[] bytes = bos.toByteArray();
                return bytes;
            } catch (IOException e) {
                e.printStackTrace();
                throw new SerialException("serialize the message object failed: " + e.getMessage());
            }
        }

        @Override
        public <T> T deserialize(Class<T> clazz, byte[] data) throws SerialException {
            try (ByteArrayInputStream bis = new ByteArrayInputStream(data); ObjectInputStream ois = new ObjectInputStream(bis)) {

                // 写入对象
                T object = (T) ois.readObject();
                return object;
            } catch (IOException e) {
                e.printStackTrace();
                throw new SerialException("serialize the message object failed: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new SerialException("deserialize the bytes data to the message object failed: " + e.getMessage());
            }
        }
    },

    // JSON 序列化
    Json("json") {
        @Override
        public <T> byte[] serialize(T object) throws SerialException {
            String json = JSON.toJSONString(object, JSONWriter.Feature.WriteMapNullValue);
            return json.getBytes(StandardCharsets.UTF_8);
        }

        @Override
        public <T> T deserialize(Class<T> clazz, byte[] data) throws SerialException {
            String json = new String(data, StandardCharsets.UTF_8);
            T object = JSON.parseObject(json, clazz);
            return object;
        }
    },

    // Protobuf序列化
    Protobuf("protobuf") {
        @Override
        public <T> byte[] serialize(T object) {

            byte[] bytes = ProtoStuffUtils.serialize(object);
            return bytes;
        }

        @Override
        public <T> T deserialize(Class<T> clazz, byte[] data) {

            T object = ProtoStuffUtils.deserialize(data, clazz);
            return object;
        }
    },


    // kryo序列化
    Kryo("kryo") {
        @Override
        public <T> byte[] serialize(T object) throws SerialException {
            KryoSerializer kryoSerializer = new KryoSerializer(object.getClass());
            byte[] bytes = kryoSerializer.serialize(object);
            return bytes;
        }

        @Override
        public <T> T deserialize(Class<T> clazz, byte[] data) throws SerialException {
            KryoSerializer kryoSerializer = new KryoSerializer(clazz);
            T object = kryoSerializer.deserialize(clazz, data);
            return object;
        }
    },


    // XML序列化
    Xml("xml") {
        @Override
        public <T> byte[] serialize(T object) throws SerialException {
            return new byte[0];
        }

        @Override
        public <T> T deserialize(Class<T> clazz, byte[] data) throws SerialException {
            return null;
        }
    },

    // HESSIAN序列化
    Hessian("hessian") {
        @Override
        public <T> byte[] serialize(T object) throws SerialException {

            HessianOutput hessianOutput = null;
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
                hessianOutput = new HessianOutput(bos);
                hessianOutput.writeObject(object);
                hessianOutput.flush();
                return bos.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
                throw new SerialException("hessian serialize the message object failed: " + e.getMessage());
            } finally {
                assert hessianOutput != null;
                try {
                    hessianOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public <T> T deserialize(Class<T> clazz, byte[] data) throws SerialException {

            HessianInput hessianInput = new HessianInput();
            try (ByteArrayInputStream bis = new ByteArrayInputStream(data)) {
                hessianInput.init(bis);
                T object = (T) hessianInput.readObject();
                return object;
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                hessianInput.close();
            }
            return null;
        }
    },

    // 默认序列化方式：protobuf
    DEFAULT("default") {
        @Override
        public <T> byte[] serialize(T object) throws SerialException {
            return SerializationEnum.Protobuf.serialize(object);
        }

        @Override
        public <T> T deserialize(Class<T> clazz, byte[] data) throws SerialException {
            return SerializationEnum.Protobuf.deserialize(clazz, data);
        }
    };


    /**
     * 序列化算法
     */
    private String algorithm;

    SerializationEnum(String algorithm) {
    }


    /**
     * 根据枚举值获取序列化方式
     *
     * @param value
     *
     * @return
     */
    public static SerializationEnum getInstance(int value) throws SerialException {

        if (value < 0 || value > SerializationEnum.values().length) {
            // 序列化协议错误
            throw new SerialException("serialization protocol erro: no such serialize algorithm");
        }
        return SerializationEnum.values()[value];
    }


    public static SerializationEnum getInstance(String value) {
        return SerializationEnum.valueOf(value);
    }
}
