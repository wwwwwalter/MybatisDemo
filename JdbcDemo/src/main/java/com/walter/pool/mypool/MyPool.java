package com.walter.pool.mypool;



import com.walter.util.DBUtil;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

public class MyPool implements DataSource {

    private static LinkedList<Connection> pool = (new LinkedList<Connection>());

    static {
        for (int i = 0; i < 10; ++i) {
            try {
                Connection connection = DBUtil.getConnection();
                pool.add(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        synchronized (MyPool.class) {
            if (pool.size() > 0) {
                Connection connection = pool.removeFirst();
                System.out.println("getFromPool:" + connection);

                MyConnection superConnection = new MyConnection(connection, pool);
                return superConnection;
            } else {
                throw new RuntimeException("服务器忙");
            }
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {

        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
