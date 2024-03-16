package com.military.cloud.common.security.config;

import cn.hutool.core.collection.CollectionUtil;
import com.military.cloud.common.security.annotation.EnableAuth;
import com.military.cloud.common.security.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * <p>
 * 鉴权拦截器的配置
 * </p>
 * Spring 5.0后，WebMvcConfigurerAdapter被废弃，取代的方法有两种：
 * <p>
 * ①implements WebMvcConfigurer（官方推荐）
 * <p>
 * ②extends WebMvcConfigurationSupport
 * 使用第一种方法是实现了一个接口，可以任意实现里面的方法，不会影响到Spring Boot自身的@EnableAutoConfiguration，
 * <p>
 * 使用第二种方法相当于覆盖了@EnableAutoConfiguration里的所有方法，每个方法都需要重写，比如，若不实现方法addResourceHandlers()，则会导致静态资源无法访问，实现的方法如下：
 *
 * @Override protected void addResourceHandlers(ResourceHandlerRegistry registry) {
 * registry.addResourceHandler("/**")
 * .addResourceLocations("classpath:/META-INF/resources/")
 * .addResourceLocations("classpath:/resources/")
 * .addResourceLocations("classpath:/static/")
 * .addResourceLocations("classpath:/public/");
 * super.addResourceHandlers(registry);
 * }
 * ————————————————
 * @auther: kevin
 * @since: 2023/5/16 16:45
 */
@Configuration
@ConditionalOnBean(annotation = EnableAuth.class)
public class WebAuthConfig implements WebMvcConfigurer { // 官方推荐 ，WebMvcConfigurerAdapter 已被废弃


    // 鉴权配置，忽略不需要鉴权的uri，nacos自行添加
    private List<String> whitelist;

    private List<String> serverPath;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册鉴权拦截器
        InterceptorRegistration interceptorRegistration = registry
                .addInterceptor(getAuthInterceptor())
                .addPathPatterns("/**");
        // 排除不需要鉴权的公共的uri
        if (CollectionUtil.isNotEmpty(whitelist)) {
            interceptorRegistration.excludePathPatterns(whitelist);
        }

        // 排除不需要鉴权的服务端自己的uri
        if (CollectionUtil.isNotEmpty(serverPath)) {
            interceptorRegistration.excludePathPatterns(serverPath);
        }
    }

    /**
     * 资源功能权限拦截
     *
     * @return
     */
    @Bean
    AuthInterceptor getAuthInterceptor() {
        return new AuthInterceptor();
    }
}
