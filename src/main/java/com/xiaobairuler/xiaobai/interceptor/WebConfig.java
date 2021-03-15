package com.xiaobairuler.xiaobai.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author 小白
 * @create 2020/4/16 14:32
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 注册拦截器，拦截/admin下的所有链接(排除/admin和/admin/login)
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }
}
