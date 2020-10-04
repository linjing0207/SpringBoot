package com.linjing.mapper;

import com.linjing.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper //这个注解表示了这是一个mybatis的mapper类: Dao
@Repository //在spring容器里
public interface UserMapper {
    //int age = 18;
    //public static final int age = 18;

    List<User> queryUserList();

    User queryUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);
}
