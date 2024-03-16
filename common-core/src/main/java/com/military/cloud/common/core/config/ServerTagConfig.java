package com.military.cloud.common.core.config;

import com.military.cloud.common.core.enums.ServerEnum;
import com.military.cloud.common.core.exception.BizException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ServerTagConfig {

    @Value("${spring.application.name}")
    private String serverTag;

    @Getter
    private ServerEnum serverEnum;


    @PostConstruct
    public void init() {
        if (StringUtils.isEmpty(this.serverTag)){
            throw new BizException("服务标识不能为空");
        }
        serverEnum = ServerEnum.getByServerName(this.serverTag);
    }

}
