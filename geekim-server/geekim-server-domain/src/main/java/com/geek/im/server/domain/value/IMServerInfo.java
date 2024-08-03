package com.geek.im.server.domain.value;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : HK意境
 * @ClassName : IMServerInfo
 * @date : 2024/4/29 19:41
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@Accessors(chain = true)
public class IMServerInfo {

    private String serverLocation;

    private String host;

    private Integer port;

    private String protocol;

    private String path;

}
