package com.geek.im.support.infrastructure.lock.distribute;

/**
 * @author : HK意境
 * @ClassName : DistributeLockFactory
 * @date : 2023/12/9 8:54
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface DistributeLockFactory {

    public DistributeLock getDistributeLock(String key);


}
