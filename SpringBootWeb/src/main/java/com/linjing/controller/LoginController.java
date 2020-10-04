package com.linjing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping(value = "/user/login",
        method = RequestMethod.POST)

    public String login(@RequestParam("username") String userName, @RequestParam("pwd") String pwd, Model model,
        HttpSession session) {
        //具体业务
        if (!StringUtils.isEmpty(userName) && pwd.equals("123456")) {
            session.setAttribute("loginUser", userName);
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "用户名或者密码错误!");
            return "index";
        }
    }
}
