package com.walter.pool;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Pool {
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource("windows");

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("服务器忙");
        }
    }

    public static void poolStatus() {
        try {
            System.out.println("闲置的：" + dataSource.getNumIdleConnections());
            System.out.println("忙碌的：" + dataSource.getNumBusyConnections());
            System.out.println("所有的：" + dataSource.getNumConnections());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
