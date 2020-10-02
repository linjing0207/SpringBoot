package com.linjing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//如果我们要扩展springMVC, 官方建议我们这么做!
@Configuration
//@EnableWebMvc  //就是导入DelegatingWebMvcConfiguration类: 从容器中获取所有的webMvcConfig
public class MyMvcConfig implements WebMvcConfigurer {
    //试图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/linjing").setViewName("test");
    }
}

