package com.geek.im.authorization.infrastructure.repository;

import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.geek.im.authorization.domain.entity.Oauth2ThirdAccount;
import com.geek.im.authorization.domain.mapper.Oauth2ThirdAccountMapper;
import com.geek.im.authorization.domain.repository.ThirdAccountRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : HK意境
 * @ClassName : ThirdAccountRepositoryImpl
 * @date : 2024/4/22 19:51
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Repository
public class ThirdAccountRepositoryImpl implements ThirdAccountRepository {

    @Resource
    private Oauth2ThirdAccountMapper oauth2ThirdAccountMapper;


    @Override
    public <S extends Oauth2ThirdAccount> S save(S entity) {

        int insert = this.oauth2ThirdAccountMapper.insert(entity);
        if (insert > 0) {
            return entity;
        }
        return null;
    }

    @Override
    public <S extends Oauth2ThirdAccount> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Oauth2ThirdAccount> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Oauth2ThirdAccount> findAll() {
        return null;
    }

    @Override
    public Iterable<Oauth2ThirdAccount> findAllById(Iterable<Long> longs) {
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
    public void delete(Oauth2ThirdAccount entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Oauth2ThirdAccount> entities) {

    }

    @Override
    public void deleteAll() {

    }


    /**
     * 获取第三方认证账户
     *
     * @param type     登录方式
     * @param uniqueId 三方登录唯一id
     *
     * @return
     */
    @Override
    public Oauth2ThirdAccount getUniqueThirdAccount(String type, String uniqueId) {

        Oauth2ThirdAccount thirdAccount = ChainWrappers.lambdaQueryChain(this.oauth2ThirdAccountMapper)
                .eq(Oauth2ThirdAccount::getType, type)
                .eq(Oauth2ThirdAccount::getUniqueId, uniqueId)
                .one();

        return thirdAccount;
    }
}
