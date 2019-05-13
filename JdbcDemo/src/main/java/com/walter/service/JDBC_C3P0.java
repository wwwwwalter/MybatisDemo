package com.walter.service;




import com.walter.pool.C3P0Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JDBC_C3P0 {
    public static void main(String[] args) {
        String username = "walter";
        String password = "walterisno1";
        String sql = "select * from user where name=? and password=?";
        C3P0Pool c3P0Pool = new C3P0Pool();
        try (
                Connection connection = c3P0Pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                c3P0Pool.poolStatus();
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

    }
}
