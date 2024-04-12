package com.geek.im.authorization.domain.authority;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author : HK意境
 * @ClassName : CustomGrantedAuthority
 * @date : 2024/4/11 21:09
 * @description : 自定义权限类
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@JsonSerialize
@NoArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {

    private String authority;

    private String role;

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public CustomGrantedAuthority(String authority) {
        this.authority = authority;
    }
}
