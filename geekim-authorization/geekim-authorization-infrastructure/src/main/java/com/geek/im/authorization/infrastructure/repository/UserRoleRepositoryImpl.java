package com.geek.im.authorization.infrastructure.repository;

import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.geek.im.authorization.domain.entity.SysUserRole;
import com.geek.im.authorization.domain.mapper.SysUserRoleMapper;
import com.geek.im.authorization.domain.repository.UserRoleRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author : HK意境
 * @ClassName : UserRoleRepositoryImpl
 * @date : 2024/4/10 20:02
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Repository
public class UserRoleRepositoryImpl implements UserRoleRepository {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;


    /**
     * 获取用户角色列表
     *
     * @param userId
     *
     * @return
     */
    @Override
    public List<SysUserRole> findUserRoles(Long userId) {

        if (Objects.isNull(userId)) {
            // 用户id为空
            return Collections.emptyList();
        }

        List<SysUserRole> sysUserRoleList = ChainWrappers.lambdaQueryChain(this.sysUserRoleMapper)
                .eq(SysUserRole::getUserId, userId)
                .list();
        return sysUserRoleList;
    }
}
