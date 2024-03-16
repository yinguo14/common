package com.military.cloud.common.core.enums;

import com.military.cloud.common.core.exception.BizException;
import lombok.Getter;

@Getter
public enum ServerEnum {
    MILITARY_TEMPLATE("服务", "military-template", "TE"),
    ;

    private final String desc;
    private final String serverName;
    private final String code;

    ServerEnum(String chineseName, String serverName, String code) {
        this.desc = chineseName;
        this.serverName = serverName;
        this.code = code;
    }

    public static ServerEnum getByCode(String code) {
        for (ServerEnum serverEnum : ServerEnum.values()) {
            if (serverEnum.getCode().equals(code)) {
                return serverEnum;
            }
        }
        throw new BizException("服务不存在");
    }

    public static ServerEnum getByServerName(String serverName) {
        for (ServerEnum serverEnum : ServerEnum.values()) {
            if (serverEnum.getServerName().equals(serverName)) {
                return serverEnum;
            }
        }
        throw new BizException("服务不存在");
    }


}