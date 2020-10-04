package com.linjing.controller;

import com.linjing.dao.DepartmentDao;
import com.linjing.dao.EmployeeDao;
import com.linjing.pojo.Department;
import com.linjing.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getEmployees();
        model.addAttribute("emps", employees);
        return "emp/list"; //返回list.html
    }

    //去员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        //页面跳转的时候, 查出部门的信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/add"; //返回add.html
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        System.out.println("save=>" + employee);
        //添加操作
        employeeDao.save(employee); //调用底层业务方法保存员工信息
        return "redirect:/emps"; //需要客户端重新发起一个新的"/emps"请求, 刷新列表
    }

    //去员工修改页面
    @GetMapping("/emp/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id, Model model) {
        //页面跳转的时候, 查出员工原来的数据
        Employee e = employeeDao.getEmployeeById(id);
        model.addAttribute("emp", e);
        //查询部门的信息, 回显给客户端
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/update";  //返回给前端update.html->然后修改完毕, submit form进入"/emp"请求
    }

    //删除员工
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id) {
        //删除操作
        employeeDao.deleteById(id);
        return "redirect:/emps"; //需要客户端重新发起一个新的"/emps"请求, 刷新列表
    }
}
