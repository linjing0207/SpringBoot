package com.linjing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Springboot04DataApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        //查看一下默认的数据源: com.zaxxer.hikari.HikariDataSource, 可以认为和dbcp一样的.
        System.out.println(dataSource.getClass());

        //获得数据库连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        //xxxx Template 模板: springboot 已经配置(封装)好的模板bean, 拿来即用. CRUD
        //jdbc
        //redis

        //关闭
        connection.close();
    }

}
