package com.walter.pool.mypool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public class MyConnection extends MyWrap {

    private Connection connection;
    private LinkedList<Connection> pool;

    public MyConnection(Connection connection, LinkedList<Connection> pool) {
        super(connection);
        this.connection = connection;
        this.pool = pool;
    }

    @Override
    public void close() throws SQLException {
        pool.addLast(connection);
        System.out.println("returnToPool:" + connection);
    }
}
