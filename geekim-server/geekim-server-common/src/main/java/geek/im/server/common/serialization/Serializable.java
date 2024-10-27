package geek.im.server.common.serialization;

import javax.sql.rowset.serial.SerialException;

/**
 * @author : HK意境
 * @ClassName : Serializable
 * @date : 2022/12/28 0:51
 * @description : 七种Java常用序列化框架的选型与对比 https://juejin.cn/post/6974565210161954829?from=search-suggest#heading-8
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface Serializable {


    /**
     * 序列化
     *
     * @param object
     * @param <T>
     *
     * @return
     *
     * @throws SerialException
     */
    public <T> byte[] serialize(T object) throws SerialException;


    /**
     * 反序列化
     *
     * @param clazz
     * @param data
     * @param <T>
     *
     * @return
     *
     * @throws SerialException
     */
    public <T> T deserialize(Class<T> clazz, byte[] data) throws SerialException;

}
