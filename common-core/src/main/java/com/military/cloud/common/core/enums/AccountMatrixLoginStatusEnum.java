package com.military.cloud.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountMatrixLoginStatusEnum {
    LOGIN_SUCCESS(1,"登录成功"),
    LOGON_EXPIRATION(0,"登录失效"),


    ;

    private int code;
    private String name;
}
