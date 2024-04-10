package com.geek.im.authorization.infrastructure.repository;

import com.geek.im.authorization.domain.entity.SysRole;
import com.geek.im.authorization.domain.mapper.SysRoleMapper;
import com.geek.im.authorization.domain.repository.SysRoleRepository;
import com.google.common.collect.Iterables;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.*;

/**
 * @author : HK意境
 * @ClassName : RoleRepositoryImpl
 * @date : 2024/4/10 19:45
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Repository
public class RoleRepositoryImpl implements SysRoleRepository {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public <S extends SysRole> S save(S role) {

        int insert = this.sysRoleMapper.insert(role);
        if (insert <= 0) {
            return null;
        }
        return role;
    }


    @Override
    public <S extends SysRole> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }


    @Override
    public Optional<SysRole> findById(Integer roleId) {
        return Optional.ofNullable(this.getById(roleId));
    }


    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<SysRole> findAll() {
        return null;
    }


    /**
     * 查询角色列表
     *
     * @param roleIds
     *
     * @return
     */
    @Override
    public Iterable<SysRole> findAllById(Iterable<Integer> roleIds) {

        if (Objects.isNull(roleIds) || Iterables.isEmpty(roleIds)) {
            return Collections.emptyList();
        }

        List<SysRole> sysRoleList = this.sysRoleMapper.selectBatchIds((Collection<? extends Serializable>) roleIds);
        return sysRoleList;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(SysRole entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends SysRole> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public SysRole getById(Integer roleId) {

        SysRole sysRole = this.sysRoleMapper.selectById(roleId);
        return sysRole;
    }
}
