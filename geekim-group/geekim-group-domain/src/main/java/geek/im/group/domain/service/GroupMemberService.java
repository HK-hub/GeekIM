package geek.im.group.domain.service;

import java.util.Set;

/**
 * @author : HK意境
 * @ClassName : GroupMemberService
 * @date : 2024/8/19 19:36
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface GroupMemberService {

    public Set<Long> getGroupMemberIds(Long groupId);

    public Long getGroupMasterId(Long groupId);

    public Boolean isGroupMember(Long groupId, Long memberId);
}
