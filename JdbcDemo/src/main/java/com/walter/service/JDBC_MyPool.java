package com.walter.service;

import com.walter.pool.DruidPool;
import com.walter.pool.mypool.MyPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_MyPool {
    public static void main(String[] args) {
        String username = "walter";
        String password = "walterisno1";
        String sql = "select * from user where name=? and password=?";
        MyPool myPool = new MyPool();
        try (
                Connection connection = myPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getObject("id"));
                    System.out.println(resultSet.getObject("name"));
                    System.out.println(resultSet.getObject("password"));
                }
            }
            //connection.close();//try()自动调用，并且这个函数被MyConnection重写了
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }
}
