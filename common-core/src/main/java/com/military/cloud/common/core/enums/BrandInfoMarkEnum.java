package com.military.cloud.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BrandInfoMarkEnum {
    chinese(1,"中文"),
    english(2,"英文"),
    ;

    private int code;
    private String name;
}
