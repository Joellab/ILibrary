package com.hongmai.clonfer.security;

import com.hongmai.clonfer.util.UserUtil;
import io.jsonwebtoken.lang.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

/**
 * 授权管理，决定当前用户是否有该请求权限
 *
 * @author JiaweiWang
 */
@Slf4j
@Component
public class MyDecisionManager implements AccessDecisionManager {

    @Autowired
    private UserUtil userUtil;

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) {
        // 如果授权规则为空则代表此URL无需授权
        if (Collections.isEmpty(configAttributes)) {
            return;
        }
        log.info("权限鉴别 -> " + "所需权限" + configAttributes + "｜用户权限" + authentication.getAuthorities());
        userUtil.checkSpecificUserLoginStatus(UserUtil.getCurrentUserName());

        // 判断授权规则和当前用户所属权限是否匹配
        for (ConfigAttribute ca : configAttributes) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                // 如果匹配上了，代表当前登录用户是有该权限的，直接结束方法
                if (Objects.equals(authority.getAuthority(), ca.getAttribute())) {
                    return;
                }
                // 如果未匹配上，判断是否拥有超级管理员权限
                if(authority.getAuthority().equals("0")) {
                    return;
                }
            }
        }
        // 走到这里就代表没有权限
        throw new AccessDeniedException("用户组权限受限");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
