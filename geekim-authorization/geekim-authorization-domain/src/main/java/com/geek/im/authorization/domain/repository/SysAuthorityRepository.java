package com.geek.im.authorization.domain.repository;

import com.geek.im.authorization.domain.entity.SysAuthority;

import java.util.Collection;
import java.util.List;

/**
 * @author : HK意境
 * @ClassName : SysAuthorityRepository
 * @date : 2024/4/10 20:22
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface SysAuthorityRepository {

    /**
     * 根据id查询权限
     *
     * @param authorityId
     *
     * @return
     */
    public SysAuthority findById(Integer authorityId);


    /**
     * 根据id列表查询权限列表
     *
     * @param authorityIds
     *
     * @return
     */
    public List<SysAuthority> findALllByIds(Collection<Integer> authorityIds);

}
