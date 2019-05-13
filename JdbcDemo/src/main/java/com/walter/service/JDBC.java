package com.walter.service;

import java.sql.*;

public class JDBC {
    public static void main(String[] args) {
        //注册驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                //获取连接Connection
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306" +
                        "/spring" +
                        "?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&useSSL" +
                        "=false", "root", "root");
                //得到执行sql语句的对象Statement
                Statement statement = connection.createStatement();
                //执行sql语句，并返回结果
                ResultSet resultSet = statement.executeQuery("select * from user;")) {

            //处理结果
            while (resultSet.next()) {
                System.out.println(resultSet.getObject("id"));
                System.out.println(resultSet.getObject("name"));
                System.out.println(resultSet.getObject("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //关闭流
    }
}
