package com.hongmai.clonfer.security;

import com.hongmai.clonfer.model.domain.CpBaseUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 自定义用户对象
 *
 * @author JiaweiWang
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class UserDetail extends org.springframework.security.core.userdetails.User {

    private CpBaseUser cpBaseUser;

    public UserDetail(CpBaseUser cpBaseUser, Collection<? extends GrantedAuthority> authorities) {
        // 必须调用父类的构造方法，初始化用户名、密码、权限
        super(cpBaseUser.getUsername(), cpBaseUser.getPassword(), authorities);
        this.cpBaseUser = cpBaseUser;
    }
}
