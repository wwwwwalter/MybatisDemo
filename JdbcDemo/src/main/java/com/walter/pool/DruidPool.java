package com.walter.pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidPool {
    private static DruidDataSource dataSource = null;

    static {
        try {
            Properties properties = new Properties();
            properties.load(DruidPool.class.getClassLoader()
                    .getResourceAsStream("dbconfig.properties"));
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("服务器忙");
        }
    }
}
