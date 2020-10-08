package com.linjing.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

//配置swagger信息
@Configuration  //等价于component
@EnableSwagger2 //开启swagger2
public class SwaggerConfig {

    @Bean
    public Docket docket1() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }

    @Bean
    public Docket docket2() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }

    @Bean
    public Docket docket3() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }

    //配置了swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment env) {
        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        //通过env.acceptsProfiles()来判断是否处在某环境下.
        boolean flag = env.acceptsProfiles(profiles);
        System.out.println(flag);
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("linjing")
            //是否启动swagger, 如果为false, 则swagger不能在浏览器中访问
            .enable(true).select()
            //RequestHandlerSelectors 配置要扫描接口的方式
            //basePackage(): 指定要扫描的包
            //any(): 扫描全部
            //none(): 不扫描
            //withClassAnnotation(): 扫描类上的注解, 参数是一个注解的反射对象
            //withMethodAnnotation(): 扫描方法上的注解
            .apis(RequestHandlerSelectors.basePackage("com.linjing.swagger.controller"))
            //过滤扫描路径
            //.paths(PathSelectors.ant("/linjing/**"))
            .build(); //工厂模式
    }

    //配置swagger信息=apiInfo
    private ApiInfo apiInfo() {
        //作者信息
        Contact contact = new Contact("linjing", "http://localhost:8080", "12@gmail.com");
        return new ApiInfo("Linjing的SwaggerAPI文档", "岁月无从贷款", "1.0", "http://localhost:8080", contact, "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    }
}
