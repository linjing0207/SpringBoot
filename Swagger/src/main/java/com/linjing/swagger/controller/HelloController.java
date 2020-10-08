package com.linjing.swagger.controller;

import com.linjing.swagger.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class HelloController {

    //一个项目一定有一个/error请求
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    //只要controller接口中, 返回值存在实体类, 它就会被扫描到swagger中
    @PostMapping("/user")
    public User user() {
        return new User();
    }

    //Operation接口, 放在方法上
    @ApiOperation("Hello控制类")
    @GetMapping("/hello2")
    public String hello2(@ApiParam("用户名") String username) {
        return username;
    }
}
