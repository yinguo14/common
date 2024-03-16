package com.military.cloud.common.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限验证注解，开启或者关闭权限
 *
 * @author: kevin
 * @since: 2023-05-16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface Authorization {
    /**
     * 权限类型
     *
     * @return
     */
    String value() default "";
}
