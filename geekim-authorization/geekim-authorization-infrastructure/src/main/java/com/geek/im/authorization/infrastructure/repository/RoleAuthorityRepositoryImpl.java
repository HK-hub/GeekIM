package com.geek.im.authorization.infrastructure.repository;

import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.geek.im.authorization.domain.entity.SysRoleAuthority;
import com.geek.im.authorization.domain.mapper.SysRoleAuthorityMapper;
import com.geek.im.authorization.domain.repository.RoleAuthorityRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author : HK意境
 * @ClassName : RoleAuthorityRepositoryImpl
 * @date : 2024/4/10 20:38
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Repository
public class RoleAuthorityRepositoryImpl implements RoleAuthorityRepository {

    @Resource
    private SysRoleAuthorityMapper sysRoleAuthorityMapper;


    /**
     * 获取角色-权限列表
     *
     * @param roleId
     *
     * @return
     */
    @Override
    public List<SysRoleAuthority> findByRoleId(Integer roleId) {

        if (Objects.isNull(roleId)) {
            return Collections.emptyList();
        }
        List<SysRoleAuthority> sysRoleAuthorities = ChainWrappers.lambdaQueryChain(this.sysRoleAuthorityMapper)
                .eq(SysRoleAuthority::getRoleId, roleId)
                .list();

        return sysRoleAuthorities;
    }


    /**
     * 获取角色-权限列表
     *
     * @param roleIds
     *
     * @return
     */
    @Override
    public List<SysRoleAuthority> findByRoleIds(Collection<Integer> roleIds) {

        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }

        List<SysRoleAuthority> sysRoleAuthorities = ChainWrappers.lambdaQueryChain(this.sysRoleAuthorityMapper)
                .in(SysRoleAuthority::getRoleId, roleIds)
                .list();
        return sysRoleAuthorities;
    }
}
