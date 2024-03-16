package com.military.cloud.common.security.annotation;

import java.lang.annotation.*;

/**
 * 启用权限验证
 *
 * @author: kevin
 * @since: 2023-05-16
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EnableAuth {
}
