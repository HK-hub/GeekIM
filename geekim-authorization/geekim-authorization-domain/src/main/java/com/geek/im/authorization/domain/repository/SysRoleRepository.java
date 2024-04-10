package com.geek.im.authorization.domain.repository;

import com.geek.im.authorization.domain.entity.SysRole;
import org.springframework.data.repository.CrudRepository;

/**
 * @author : HK意境
 * @ClassName : SysRoleRepository
 * @date : 2024/4/10 17:24
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface SysRoleRepository extends CrudRepository<SysRole, Integer> {


    public SysRole getById(Integer roleId);

}
