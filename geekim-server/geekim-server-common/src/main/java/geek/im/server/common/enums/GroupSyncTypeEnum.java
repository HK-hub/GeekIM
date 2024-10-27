package geek.im.server.common.enums;

import lombok.Getter;

/**
 * @author : HK意境
 * @ClassName : GroupSyncTypeEnum
 * @date : 2024/8/17 22:27
 * @description : 大群同步方式
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Getter
public enum GroupSyncTypeEnum {

    // 集群之间进行广播:如果群组成员不多，可以考虑广播查询的方式，即将请求发送给所有服务器，
    // 服务器自行检查是否有在线的群组成员。这种方式适合于群组成员较少或集群节点较少的场景。
    broadcast,
    // 查询用户的在线状态和所在的服务器位置：然后将消息发往这些服务器
    index;
}
