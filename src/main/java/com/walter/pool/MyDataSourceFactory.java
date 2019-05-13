package com.walter.pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

//写给mybatis调用
public class MyDataSourceFactory implements DataSourceFactory {

    private static DruidDataSource dataSource;

    @Override
    public void setProperties(Properties properties) {
        //不用参数里的properties,来自mybatis.xml
    }

    @Override
    public DataSource getDataSource() {
        try {
            Properties properties = new Properties();
            properties.load(MyDataSourceFactory.class.getClassLoader()
                    .getResourceAsStream("dbconfig.properties"));
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataSource;
    }

    public static void dataSourceStatus() {
        System.out.println(dataSource);
        dataSource.setMaxActive(21);
        //作用同配置文件
    }
}
