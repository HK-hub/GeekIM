package com.geek.im.authorization.application.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geek.im.authorization.domain.authority.CustomGrantedAuthority;
import com.geek.im.authorization.domain.entity.*;
import com.geek.im.authorization.domain.mapper.Oauth2BasicUserMapper;
import com.geek.im.authorization.domain.repository.BasicUserRepository;
import com.geek.im.authorization.domain.repository.RoleAuthorityRepository;
import com.geek.im.authorization.domain.repository.SysAuthorityRepository;
import com.geek.im.authorization.domain.repository.UserRoleRepository;
import com.geek.im.authorization.domain.service.Oauth2BasicUserService;
import com.geek.im.authorization.infrastructure.repository.RoleRepositoryImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : HK意境
 * @ClassName : Oauth2BasicUserServiceImpl
 * @date : 2024/4/10 17:15
 * @description : 用户认证服务
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Slf4j
@Service
public class Oauth2BasicUserServiceImpl extends ServiceImpl<Oauth2BasicUserMapper, Oauth2BasicUser>
        implements Oauth2BasicUserService, UserDetailsService, UserDetailsManager {

    @Resource
    private BasicUserRepository basicUserRepository;
    @Resource
    private RoleRepositoryImpl roleRepository;
    @Resource
    private UserRoleRepository userRoleRepository;

    @Resource
    private RoleAuthorityRepository roleAuthorityRepository;
    @Resource
    private SysAuthorityRepository sysAuthorityRepository;


    /**
     * 根据用户名加载用户
     *
     * @param username
     *
     * @return
     *
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 在Security中username就代表了用户登录时输入的账号，再重写该方法时它可以代表一下内容：账号，手机号，邮箱，姓名等。
        // “username”在数据库中不一定非要是一样的列，它可以是手机号、邮箱，也可以都是，最主要的目的就是根据输入的内容获取到对应的用户信息，如下方所示
        // 通过传入的账号信息查询对应的用户信息
        Oauth2BasicUser basicUser = this.basicUserRepository.findUserByUsername(username);

        if (Objects.isNull(basicUser)) {
            // 用户不存在
            throw new UsernameNotFoundException("账号不存在!");
        }

        // 查询用户的角色列表
        List<SysUserRole> sysUserRoleList = this.userRoleRepository.findUserRoles(basicUser.getId());
        // 根据用户角色查询出菜单
        Set<Integer> roleIdSet = sysUserRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toSet());
        List<SysRoleAuthority> sysRoleAuthorityList = this.roleAuthorityRepository.findByRoleIds(roleIdSet);

        // 查询出角色列表
        Collection<SysRole> roles = (Collection<SysRole>) this.roleRepository.findAllById(roleIdSet);
        basicUser.setRoles(roles);

        // 根据菜单查出具体权限信息
        Set<Integer> authorityIdSet = sysRoleAuthorityList.stream().map(SysRoleAuthority::getAuthorityId).collect(Collectors.toSet());
        List<SysAuthority> sysAuthorityList = this.sysAuthorityRepository.findALllByIds(authorityIdSet);

        // 转换为授权信息
        Set<CustomGrantedAuthority> authoritySet = sysAuthorityList.stream().map(authority -> new CustomGrantedAuthority(authority.getUrl()))
                .collect(Collectors.toSet());

        basicUser.setAuthorities(authoritySet);
        return basicUser;
    }


    /**
     * 创建用户
     *
     * @param user
     */
    @Override
    public void createUser(UserDetails user) {

        // 转换为basicUser对象
        Oauth2BasicUser basicUser = (Oauth2BasicUser) user;
        // 检查用户是否存在
        Oauth2BasicUser existsUser = this.getUserByAccount(basicUser);
        if (Objects.nonNull(existsUser)) {
            // 用户已存在
            throw new OAuth2AuthenticationException(new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST), "User already exists!");
        }

        // 保存用户
        this.basicUserRepository.save(basicUser);
    }


    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }


    /**
     * 修改密码服务，需要从请求头中获取具体用户
     *
     * @param oldPassword
     * @param newPassword
     */
    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }


    /**
     * 用户是否存在
     *
     * @param account
     *
     * @return
     */
    @Override
    public boolean userExists(String account) {

        Oauth2BasicUser user = this.basicUserRepository.findUserByUsername(account);
        return Objects.nonNull(user);
    }


    /**
     * 根据账户获取用户信息
     *
     * @param basicUser
     *
     * @return
     */
    @Override
    public Oauth2BasicUser getUserByAccount(Oauth2BasicUser basicUser) {

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

        return this.basicUserRepository.findUserByAccount(basicUser);
    }


    /**
     * 根据第三方账户信息保存基础用户信息
     *
     * @param thirdAccount
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Oauth2BasicUser saveByThirdAccount(Oauth2ThirdAccount thirdAccount) {

        Oauth2BasicUser basicUser = new Oauth2BasicUser();
        basicUser.setUsername(thirdAccount.getThirdUsername());
        basicUser.setAvatarUrl(thirdAccount.getAvatar());
        basicUser.setSourceFrom(thirdAccount.getType());
        basicUser.setDeleted(false);
        basicUser.setAccount(thirdAccount.getName());

        boolean save = this.save(basicUser);
        return basicUser;
    }


    /**
     * 根据第三方登录账户信息更新基础用户
     *
     * @param userId
     * @param thirdAccount
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Oauth2BasicUser updateBasicUserByThirdAccount(Long userId, Oauth2ThirdAccount thirdAccount) {

        Oauth2BasicUser oauth2BasicUser = this.basicUserRepository.findById(userId).orElse(null);
        if (Objects.isNull(oauth2BasicUser)) {
            // 基础用户信息不存在
            throw new AuthenticationCredentialsNotFoundException("第三方账户对应基础用户信息不存在!");
        }

        oauth2BasicUser.setUsername(thirdAccount.getThirdUsername());
        oauth2BasicUser.setAccount(thirdAccount.getName());
        oauth2BasicUser.setAvatarUrl(thirdAccount.getAvatar());
        oauth2BasicUser.setUpdateTime(null);

        this.updateById(oauth2BasicUser);
        return oauth2BasicUser;
    }
}




