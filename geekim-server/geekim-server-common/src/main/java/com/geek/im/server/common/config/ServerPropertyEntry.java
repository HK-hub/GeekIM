package com.geek.im.server.common.config;

import com.geek.im.server.common.util.LocalSocketUtil;
import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author : HK意境
 * @ClassName : ServerPropertyEntry
 * @date : 2024/4/26 23:01
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class ServerPropertyEntry {

    /**
     * epoll 网络IO模型
     */
    public static final Pair<String, Boolean> EPOLL_MODEL = Pair.of("epoll", true);

    public static final Pair<String, String> HOST = Pair.of("host", LocalSocketUtil.getLocalHost());

    public static final Pair<String, Integer> PORT = Pair.of("port", 0);

    public static final Pair<String, Integer> BOSS_GROUP_THREADS = Pair.of("boss_group_threads", 1);

    public static final Pair<String, Integer> WORKER_GROUP_THREADS = Pair.of("worker_group_threads",
            Math.max(1, SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2)));

    private static final ServerPropertySetting DEFAULTS = ServerPropertySetting.create();

    static {
        DEFAULTS.set(EPOLL_MODEL);
        DEFAULTS.set(HOST);
        DEFAULTS.set(PORT);
        DEFAULTS.set(BOSS_GROUP_THREADS);
        DEFAULTS.set(WORKER_GROUP_THREADS);
    }


    public static ServerPropertySetting getDefaults() {

        return DEFAULTS;
    }
}
