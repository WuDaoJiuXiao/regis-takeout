package com.jiuxiao.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * SpringMVC 配置类
 * @Author: 悟道九霄
 * @Date: 2022年07月31日 20:12
 * @Version: 1.0.0
 */
@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * @param registry
     * @return: void
     * @decription 设置静态资源路径映射
     * @date 2022/7/31 20:16
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("执行了资静态源路径映射...");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }
}