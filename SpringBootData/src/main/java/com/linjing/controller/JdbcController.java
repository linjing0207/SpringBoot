package com.linjing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

//controller是spring的, RestController是web的
@RestController //返回字符串
public class JdbcController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //查询数据库的所有信息
    //没有实体类, 数据库中的东西怎么获取? 万能map
    @GetMapping("/userList")
    public List<Map<String, Object>> userList() {
        String sql = "select * from user";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/addUser")
    public String addUser() {
        String sql = "insert into mybatis.user(id, name, pwd) values (4, 'jojo', '123')";
        //还帮我们自动提交了事务
        jdbcTemplate.update(sql);
        return "add success!";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id) {
        String sql = "update mybatis.user set name=?, pwd=? where id=" + id;
        //封装
        Object[] objects = new Object[2];
        objects[0] = "juju";
        objects[1] = "zzz";
        jdbcTemplate.update(sql, objects);
        return "update success!";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        String sql = "delete from mybatis.user where id=?";
        //还帮我们自动提交了事务
        jdbcTemplate.update(sql, id);
        return "delete success!";
    }
}
