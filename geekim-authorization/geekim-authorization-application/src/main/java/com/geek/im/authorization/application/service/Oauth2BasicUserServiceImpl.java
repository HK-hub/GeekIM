package com.geek.im.authorization.application.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

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
        Set<SimpleGrantedAuthority> authoritySet = sysAuthorityList.stream().map(authority -> new SimpleGrantedAuthority(authority.getUrl()))
                .collect(Collectors.toSet());

        basicUser.setAuthorities(authoritySet);
        return basicUser;
    }


    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }
}




