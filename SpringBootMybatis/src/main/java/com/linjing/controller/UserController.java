package com.linjing.controller;

import com.linjing.mapper.UserMapper;
import com.linjing.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //返回字符串
public class UserController {

    @Autowired
    private UserMapper userMapper;

    //不用写事务了

    @GetMapping("/queryUserList")
    public List<User> queryUserList() {
        List<User> userList = userMapper.queryUserList();
        for (User user : userList) {
            System.out.println(user);
        }
        return userList;
    }

    @GetMapping("/queryUserById/{id}")
    public User queryUserById(@PathVariable("id") int id) {
        return userMapper.queryUserById(id);
    }

    @GetMapping("/addUser")
    public String addUser() {
        userMapper.addUser(new User(5, "kit", "234"));
        return "add ok";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id) {
        userMapper.updateUser(new User(id, "kit", "euwfho"));
        return "update ok!";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userMapper.deleteUser(id);
        return "delete ok!";
    }
}
