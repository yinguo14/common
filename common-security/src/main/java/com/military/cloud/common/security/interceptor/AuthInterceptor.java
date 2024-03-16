package com.military.cloud.common.security.interceptor;

import com.military.cloud.common.security.annotation.Authorization;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 权限拦截器
 * HandlerInterceptorAdapter---AsyncHandlerInterceptor---HandlerInterceptor
 *
 * @author: kevin
 * @since: 2021-11-19
 */
public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);

    /**
     * 拦截request进行权限验证
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param handler  处理器
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!HandlerMethod.class.isInstance(handler)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        LOGGER.info("当前请求接口：{}", "/" + handlerMethod.getMethod().getName());

        Authorization auth = handlerMethod.getMethodAnnotation(Authorization.class);
        if (auth == null) {
            auth = handlerMethod.getBeanType().getAnnotation(Authorization.class);
        }
        if (auth == null || !(auth.value() == "WEB") || auth.value() == "NONE") {
            return true;
        }
        try {
            String uri = request.getHeader("X-SERVICE-PREFIX");
            LOGGER.info("当前权限验证的地址:{}", uri);
            if (StringUtils.isEmpty(uri)) {
                return false;
            }
            if (uri.contains("%2F")) {
                uri = uri.replaceAll("%2F", "/");
            }
//            return AuthUtil.checkPerm(uri);
            return true;
        } catch (Exception e) {
            LOGGER.error("鉴权失败!", e);
//            throw new BizException(e.getMessage());
            return false;
        }
    }
}