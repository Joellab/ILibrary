package com.hongmai.clonfer.annotation;

import java.lang.annotation.*;

/**
 * @author JiaweiWang
 * @date 2021/9/4
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {
    /**
     * 日志描述信息
     *
     * @return
     */
    String description() default "";

}
