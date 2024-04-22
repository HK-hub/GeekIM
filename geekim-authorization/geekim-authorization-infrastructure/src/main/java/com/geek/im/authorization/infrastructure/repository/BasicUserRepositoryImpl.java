package com.geek.im.authorization.infrastructure.repository;

import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.geek.im.authorization.domain.entity.Oauth2BasicUser;
import com.geek.im.authorization.domain.mapper.Oauth2BasicUserMapper;
import com.geek.im.authorization.domain.repository.BasicUserRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
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


    /**
     * 根据账户获取用户
     *
     * @param basicUser
     *
     * @return
     */
    @Override
    public Oauth2BasicUser findUserByAccount(Oauth2BasicUser basicUser) {

        if (Objects.isNull(basicUser)) {
            return null;
        }

        // 获取用户账户信息
        String account = basicUser.getAccount();
        String email = basicUser.getEmail();
        String mobile = basicUser.getMobile();

        boolean check = Objects.isNull(account) && Objects.isNull(email) && Objects.isNull(mobile);
        if (BooleanUtils.isTrue(check)) {
            // 所有账户信息都为空
            return null;
        }

        List<Oauth2BasicUser> userList = ChainWrappers.lambdaQueryChain(this.oauth2BasicUserMapper)
                .or(wrapper -> wrapper.eq(Objects.nonNull(basicUser.getId()), Oauth2BasicUser::getId, basicUser.getId()))
                .or(wrapper -> wrapper.eq(Objects.nonNull(account), Oauth2BasicUser::getAccount, account))
                .or(wrapper -> wrapper.eq(Objects.nonNull(email), Oauth2BasicUser::getEmail, email))
                .or(wrapper -> wrapper.eq(Objects.nonNull(mobile), Oauth2BasicUser::getMobile, mobile))
                .list();

        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }

        // 存在多个用户
        if (userList.size() > 1) {
            throw new OAuth2AuthorizationException(
                    new OAuth2Error("too many user found by username:" + account));
        }

        return userList.get(0);
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
