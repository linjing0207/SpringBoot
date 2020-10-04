package com.linjing.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration  //可以想成<beans>标签
public class DruidConfig {
    //这个class就是对应原来的一个一个bean-xml

    //因为springboot自动配置的是hikari, 如果想要用druid, 必须先导入依赖, 且把yaml和这个DruidConfig配置类绑起来(仿照源码)
    //因为我们想用一些私有化属性(在yaml中写的), 必须绑定才能生效
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    //druid高级功能: 后台监控: web.xml
    //因为springboot内置了servlet容器, 所以没有web.xml, 替代方法: 将ServletRegistrationBean注册进去
    //注册servletBean, 把Druid放进来
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean =
            new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //后台需要有人登陆, 账号密码配置
        HashMap<String, String> initParameters = new HashMap<>();
        //增加配置
        initParameters.put("loginUsername", "admin"); //登陆的Key是固定的
        initParameters.put("loginPassword", "123456"); //loginUsername, loginPassword
        //允许谁能访问
        initParameters.put("allow", ""); //一般写具体的人
        //禁止谁访问  initParameters.put("lili", "192.168.11.123");

        bean.setInitParameters(initParameters); //设置初始化参数
        return bean;
    }

    //filter
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        //可以过滤哪些请求呢?
        HashMap<String, String> initParameters = new HashMap<>();
        //这些东西不进行统计
        initParameters.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParameters);
        return bean;
    }

}
