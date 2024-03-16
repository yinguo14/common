package com.military.cloud.common.security.filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author guo.yin
 * 认证过滤，只要实现AuthConfigAdapter接口，添加对应路径即可
 * @date 2024/03/13 11:29
 **/
@Slf4j
@Component
public class AuthFilter implements Filter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }
}
