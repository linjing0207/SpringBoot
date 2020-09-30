package com.linjing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component //注册bean, 为了被扫描到
@ConfigurationProperties(prefix = "person") //配置类的值可以和配置文件yaml里面绑定起来
@Validated //数据校验
public class Person {
    //Person
    //(name=linjing,
    //age=18,
    //birth=Wed Jan 01 00:00:00 CST 2020,
    //map={k1=v1, k2=v2},
    //list=[code, music, girl],
    //dog=Dog(name=旺旺, age=3))
    private String name;
    private Integer age;
    @Email(message = "邮箱格式错误")
    private String email;
    private Date birth;
    private Map<String, Object> map;
    private List<String> list;
    private Dog dog;

    //无参构造, 有参构造, get/set, toString
}
