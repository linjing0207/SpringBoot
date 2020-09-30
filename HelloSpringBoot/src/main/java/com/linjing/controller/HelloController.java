package com.linjing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//springboot 导入依赖可以热部署


//@RestController
@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
