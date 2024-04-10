package com.geek.im.authorization.domain.repository;

import com.geek.im.authorization.domain.entity.SysRoleAuthority;

import java.util.Collection;
import java.util.List;

/**
 * @author : HK意境
 * @ClassName : RoleAuthorityRepository
 * @date : 2024/4/10 20:25
 * @description : 角色权限仓储类
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface RoleAuthorityRepository {


    /**
     * 获取角色-权限列表
     *
     * @param roleId
     *
     * @return
     */
    public List<SysRoleAuthority> findByRoleId(Integer roleId);


    /**
     * 获取角色-权限列表
     *
     * @param roleIds
     *
     * @return
     */
    public List<SysRoleAuthority> findByRoleIds(Collection<Integer> roleIds);


}
