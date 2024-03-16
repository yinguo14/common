package com.military.cloud.common.core.response;

import com.military.cloud.common.core.config.ServerTagConfig;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ResponseAspect {

    @Autowired
    private ServerTagConfig serverTagConfig;

    @Around("execution(* com.military.cloud.*.controller..*.*(..))")
    public Object after(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnValue = joinPoint.proceed();
        if (returnValue instanceof ServerResponseEntity) {
            ServerResponseEntity responseEntity = (ServerResponseEntity) returnValue;
            responseEntity.setSys(serverTagConfig.getServerEnum().getCode());
            return responseEntity;
        }
        return returnValue;
    }

}
