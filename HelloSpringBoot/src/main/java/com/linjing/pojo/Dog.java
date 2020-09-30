package com.linjing.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Dog {

    //自动注入
    @Value("旺旺")
    private String name;
    @Value("3")
    private Integer age;

    //无参构造, 有参构造, get/set, toString
}
