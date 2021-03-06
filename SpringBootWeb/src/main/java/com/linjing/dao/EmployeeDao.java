package com.linjing.dao;

import com.linjing.pojo.Department;
import com.linjing.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//员工dao
@Repository
public class EmployeeDao {
    //模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;
    //员工有所属的部门: 外表
    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<Integer, Employee>();
        employees.put(1001, new Employee(1001, "AA", "A12345@gmail.com", 0, new Department(101, "教学部")));
        employees.put(1002, new Employee(1002, "BB", "B12345@gmail.com", 1, new Department(102, "市场部")));
        employees.put(1003, new Employee(1003, "CC", "C12345@gmail.com", 0, new Department(103, "教研部")));
        employees.put(1004, new Employee(1004, "DD", "D12345@gmail.com", 1, new Department(104, "运营部")));
        employees.put(1005, new Employee(1005, "EE", "E12345@gmail.com", 0, new Department(105, "后勤部")));
    }

    //获得所有员工信息
    public Collection<Employee> getEmployees() {
        return employees.values();
    }

    //通过id查找员工
    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    //主键自增
    private static Integer initId = 1006;

    //增加员工
    public void save(Employee employee) {
        //设置id
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        //设置部门: 因为前端写入的值是department的id, 还需要去departmentDao中查询这个id, 返回department.
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    //删除员工
    public void deleteById(Integer id) {
        employees.remove(id);
    }

}
