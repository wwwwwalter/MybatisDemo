package com.walter.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.walter.pool.DruidPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_Druid {
    public static void main(String[] args) {
        String username = "walter";
        String password = "walterisno1";
        String sql = "select * from user where name=? and password=?";
        try (
                Connection connection = DruidPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            DruidPool.poolStatus();
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getObject("id"));
                    System.out.println(resultSet.getObject("name"));
                    System.out.println(resultSet.getObject("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        DruidPool.poolStatus();

    }
}
