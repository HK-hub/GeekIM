package com.geek.im.authorization.domain.repository;

import com.geek.im.authorization.domain.entity.SysUserRole;

import java.util.List;

/**
 * @author : HK意境
 * @ClassName : UserRoleRepository
 * @date : 2024/4/10 17:24
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface UserRoleRepository {

    /**
     * 获取用户的角色列表
     *
     * @param userId
     *
     * @return
     */
    public List<SysUserRole> findUserRoles(Long userId);


}
