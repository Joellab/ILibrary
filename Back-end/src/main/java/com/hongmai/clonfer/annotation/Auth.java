package com.hongmai.clonfer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Auth {
    /**
     * 权限id，模块id + 方法id需要唯一
     */
    int id();
    /**
     * 权限名称
     */
    String name();
}
