package com.walter.pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidPool {
    private static DruidDataSource dataSource = null;

    //先建一个空池子
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

    //当有人来要connection时创建10个，返回一个
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("服务器忙");
        }
    }

    public static void poolStatus(){
        System.out.println(dataSource);
    }
}
