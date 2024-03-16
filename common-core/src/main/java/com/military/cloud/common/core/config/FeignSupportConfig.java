package com.military.cloud.common.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * Feign配置注册（全局）
 *
 * @author YXF
 * @date 2021/05/19
 */
@Slf4j
@Configuration
public class FeignSupportConfig {

    /*@Bean
    public RequestInterceptor requestInterceptor(){
        log.info("FeignSupportConfig requestInterceptor init success..");
        return new FeignBasicAuthRequestInterceptor();
    }*/

    /**
     * 覆写拦截器，在feign发送请求前取出原来的header并转发
     *
     * @return 拦截器
     *//*
    @Bean
    public RequestInterceptor requestInterceptor() {
        return (template) -> {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
                HttpServletRequest request = attributes.getRequest();
                //获取请求头
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        String values = request.getHeader(name);
                        if (checkEssential(name)) {
                            //将请求头保存到模板中
                            template.header(name, values);
                        }
                    }
                }
            }
        };
    }

    *//**
     * 请求头必要传递参数过滤,只携带必要请求头信息
     *
     * @param name
     * @return
     *//*
    private boolean checkEssential(String name) {
        ArrayList<String> headers = Lists.newArrayList(AUTHENTICATION);
        return headers.stream().anyMatch(e -> e.equalsIgnoreCase(name));
    }*/
}
