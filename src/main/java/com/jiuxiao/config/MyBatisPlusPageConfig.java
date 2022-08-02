package com.jiuxiao.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisPLus 分页插件配置类
 * @Author: 悟道九霄
 * @Date: 2022年08月02日 15:20
 * @Version: 1.0.0
 */
@Configuration
public class MyBatisPlusPageConfig {

    /**
     * @return: com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
     * @decription 分页拦截器
     * @date 2022/8/2 15:23
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}