package com.military.cloud.common.database.annotations;

import java.lang.annotation.*;

/**
 * @author hcf
 * @Description:
 * @date 2023/5/24 14:47
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TenantIgnore {
    /** 租户控制 */
    boolean tenantLine() default false;
}
