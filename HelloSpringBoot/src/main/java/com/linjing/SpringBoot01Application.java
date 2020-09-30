package com.linjing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//主程序
// @SpringBootApplication : 标注这个类是一个springboot的应用: 启动类下的所有资源被导入
@SpringBootApplication
public class SpringBoot01Application {

    public static void main(String[] args) {
        //run()是一个静态方法
        //将springboot应用启动
        //run方法
        SpringApplication.run(SpringBoot01Application.class, args);
    }

}
