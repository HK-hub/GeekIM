package com.geek.im.authorization.infrastructure.repository;

import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.geek.im.authorization.domain.entity.Oauth2BasicUser;
import com.geek.im.authorization.domain.mapper.Oauth2BasicUserMapper;
import com.geek.im.authorization.domain.repository.BasicUserRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author : HK意境
 * @ClassName : BasicUserRepositoryImpl
 * @date : 2024/4/10 16:53
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Repository
public class BasicUserRepositoryImpl implements BasicUserRepository {

    @Resource
    private Oauth2BasicUserMapper oauth2BasicUserMapper;

    /**
     * 根据用户名查询用户
     *
     * @param username
     *
     * @return
     */
    @Override
    public Oauth2BasicUser findUserByUsername(String username) {

        List<Oauth2BasicUser> basicUserList = ChainWrappers.lambdaQueryChain(this.oauth2BasicUserMapper)
                .or(wrapper -> wrapper.eq(Oauth2BasicUser::getAccount, username))
                .or(wrapper -> wrapper.eq(Oauth2BasicUser::getMobile, username))
                .or(wrapper -> wrapper.eq(Oauth2BasicUser::getEmail, username))
                .list();

        // 用户不存在
        if (CollectionUtils.isEmpty(basicUserList)) {
            return null;
        }

        // 存在多个用户
        if (basicUserList.size() > 1) {
            throw new OAuth2AuthorizationException(
                    new OAuth2Error("too many user found by username:" + username));
        }

        return basicUserList.get(0);
    }


    @Override
    public <S extends Oauth2BasicUser> S save(S entity) {

        int insert = this.oauth2BasicUserMapper.insert(entity);
        if (insert <= 0) {
            return null;
        }
        return entity;
    }


    @Override
    public <S extends Oauth2BasicUser> Iterable<S> saveAll(Iterable<S> entities) {

        List<S> result = new ArrayList<>();
        for (S entity : entities) {
            S save = this.save(entity);
            if (Objects.nonNull(save)) {
                result.add(save);
            }
        }
        return result;
    }


    @Override
    public Optional<Oauth2BasicUser> findById(Long userId) {
        Oauth2BasicUser basicUser = this.oauth2BasicUserMapper.selectById(userId);

        return Optional.ofNullable(basicUser);
    }


    @Override
    public boolean existsById(Long userId) {
        boolean exists = ChainWrappers.lambdaQueryChain(this.oauth2BasicUserMapper)
                .eq(Oauth2BasicUser::getId, userId)
                .exists();
        return exists;
    }


    @Override
    public Iterable<Oauth2BasicUser> findAll() {

        List<Oauth2BasicUser> basicUserList = ChainWrappers.lambdaQueryChain(this.oauth2BasicUserMapper)
                .list();
        return basicUserList;
    }

    @Override
    public Iterable<Oauth2BasicUser> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Oauth2BasicUser entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Oauth2BasicUser> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
