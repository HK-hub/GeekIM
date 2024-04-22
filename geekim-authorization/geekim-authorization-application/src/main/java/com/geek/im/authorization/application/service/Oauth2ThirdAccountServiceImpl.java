package com.geek.im.authorization.application.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geek.im.authorization.domain.entity.Oauth2BasicUser;
import com.geek.im.authorization.domain.entity.Oauth2ThirdAccount;
import com.geek.im.authorization.domain.mapper.Oauth2ThirdAccountMapper;
import com.geek.im.authorization.domain.repository.ThirdAccountRepository;
import com.geek.im.authorization.domain.service.Oauth2BasicUserService;
import com.geek.im.authorization.domain.service.Oauth2ThirdAccountService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author : HK意境
 * @ClassName : Oauth2ThirdAccountServiceImpl
 * @date : 2024/4/22 19:54
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Service
public class Oauth2ThirdAccountServiceImpl extends ServiceImpl<Oauth2ThirdAccountMapper, Oauth2ThirdAccount>
        implements Oauth2ThirdAccountService {

    @Resource
    private ThirdAccountRepository oauth2ThirdAccountRepository;
    @Resource
    private Oauth2BasicUserService oauth2BasicUserService;


    /**
     * 检查添加或更新第三方账户信息
     *
     * @param thirdAccount
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean checkAndSaveUser(Oauth2ThirdAccount thirdAccount) {

        // 查询第三方登录唯一账户
        Oauth2ThirdAccount oauth2ThirdAccount = this.oauth2ThirdAccountRepository.getUniqueThirdAccount(thirdAccount.getType(), thirdAccount.getUniqueId());

        if (Objects.isNull(oauth2ThirdAccount)) {
            // 第三方认证账户不存在
            // 保存Oauth2BasicUser信息
            Oauth2BasicUser oauth2BasicUser = this.oauth2BasicUserService.saveByThirdAccount(thirdAccount);

            thirdAccount.setUserId(oauth2BasicUser.getId());
            this.oauth2ThirdAccountRepository.save(thirdAccount);
            return true;
        }

        // 校验是否需要生成基础用户信息
        if (ObjectUtils.isEmpty(oauth2ThirdAccount.getUserId())) {
            // 生成用户信息
            Oauth2BasicUser oauth2BasicUser = this.oauth2BasicUserService.saveByThirdAccount(thirdAccount);
            thirdAccount.setUserId(oauth2BasicUser.getId());
        }

        // 存在更新认证信息
        oauth2ThirdAccount.setCredentials(thirdAccount.getCredentials());
        oauth2ThirdAccount.setCredentialsExpiresAt(thirdAccount.getCredentialsExpiresAt());
        // 设置空，让Mybatis Plus自动填充
        oauth2ThirdAccount.setUpdateTime(null);

        // 更新基础用户信息
        Oauth2BasicUser oauth2BasicUser = this.oauth2BasicUserService.updateBasicUserByThirdAccount(oauth2ThirdAccount.getUserId(), thirdAccount);
        this.updateById(oauth2ThirdAccount);
        return true;
    }
}




