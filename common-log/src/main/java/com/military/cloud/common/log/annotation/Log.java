package com.military.cloud.common.log.annotation;

import java.lang.annotation.*;

/**
 * @author guo.yin
 * @date 2024/03/08 15:41
 **/
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     */
    String title() default "";
}
