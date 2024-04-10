package com.geek.im.authorization.infrastructure.repository;

import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.geek.im.authorization.domain.entity.SysAuthority;
import com.geek.im.authorization.domain.mapper.SysAuthorityMapper;
import com.geek.im.authorization.domain.repository.SysAuthorityRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author : HK意境
 * @ClassName : AuthorityRepositoryImpl
 * @date : 2024/4/10 20:53
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Repository
public class AuthorityRepositoryImpl implements SysAuthorityRepository {

    @Resource
    private SysAuthorityMapper sysAuthorityMapper;


    /**
     * 根据id查询权限信息
     *
     * @param authorityId
     *
     * @return
     */
    @Override
    public SysAuthority findById(Integer authorityId) {

        SysAuthority authority = ChainWrappers.lambdaQueryChain(this.sysAuthorityMapper)
                .eq(SysAuthority::getId, authorityId)
                .one();

        return authority;
    }


    /**
     * 查询权限信息列表
     *
     * @param authorityIds
     *
     * @return
     */
    @Override
    public List<SysAuthority> findALllByIds(Collection<Integer> authorityIds) {

        if (CollectionUtils.isEmpty(authorityIds)) {
            return Collections.emptyList();
        }

        List<SysAuthority> authorityList = this.sysAuthorityMapper.selectBatchIds(authorityIds);
        return authorityList;
    }
}
