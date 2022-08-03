package com.jiuxiao.config;

import com.jiuxiao.tools.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

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

    /**
     * @param converters
     * @return: void
     * @decription 扩展 MVC 的消息转换器
     * @date 2022/8/3 10:52
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建消息转换器
        MappingJackson2HttpMessageConverter mc = new MappingJackson2HttpMessageConverter();
        //设置对象转换器
        mc.setObjectMapper(new JacksonObjectMapper());
        //将转换器追加到MVC框架的转换器集合中，自定义的转换器必须在最前面，否则不生效
        converters.add(0, mc);
    }
}