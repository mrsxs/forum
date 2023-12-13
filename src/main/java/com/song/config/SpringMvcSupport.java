package com.song.config;

import com.song.controller.interceptor.ProjectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Administrator
 */
@Configuration //声明当前类是一个配置类
public class SpringMvcSupport extends WebMvcConfigurationSupport {
    @Autowired
    private ProjectInterceptor projectInterceptor;

    //配置静态资源
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }


    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器 拦截规则 /**表示拦截所有请求 求
        registry.addInterceptor(projectInterceptor).addPathPatterns("/**");
    }
}
