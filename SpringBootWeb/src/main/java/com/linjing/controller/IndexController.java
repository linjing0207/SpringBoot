package com.linjing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

//在resources/templates/下的所有页面, 只能通过controller来跳转
//需要模板引擎的支持! thymeLeaf
@Controller
public class IndexController {

    @RequestMapping("/test")
    public String index(Model model) {
        //model.addAttribute("msg", "Hello, Springboot!");
        model.addAttribute("msg", "<h1>Hello, Springboot!</h1>");
        model.addAttribute("users", Arrays.asList("linda", "lucy"));
        return "test";
    }
}
