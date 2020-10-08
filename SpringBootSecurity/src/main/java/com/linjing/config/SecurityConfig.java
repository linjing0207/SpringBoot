package com.linjing.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//这个类被spring托管了
//AOP: 拦截器!
@EnableWebSecurity //2
public class SecurityConfig extends WebSecurityConfigurerAdapter { //1

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页: 所有人可以访问
        //功能页: 只有有对应权限的人可以访问
        //请求授权的规则
        http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/level1/**").hasRole("vip1")
            .antMatchers("/level2/**").hasRole("vip2").antMatchers("/level3/**").hasRole("vip3");
        //没有权限默认回到登录页面, 需要开启登录的页面;
        // "/login"
        http.formLogin();
    }

    //认证: springboot 2.1.x 可以直接使用
    //密码编码: PasswordEncoder
    //在spring security 5.0+新增了很多加密方法
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //因为没连数据库, 在内存里虚拟一个数据, 来做认证
        //这些数据正常应该在数据库中读
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("linjing")
            .password(new BCryptPasswordEncoder().encode("123456")).roles("vip2", "vip3").and().withUser("root")
            .password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2", "vip3").and()
            .withUser("visitor").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1");
    }
}
