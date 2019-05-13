package com.walter.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {
    private static volatile SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession() {

        if (sqlSessionFactory == null) {
            try (InputStream inputStream =
                         MyBatisUtil.class.getClassLoader().getResourceAsStream("mybatis.xml")) {
                synchronized (MyBatisUtil.class) {
                    if (sqlSessionFactory == null) {
                        sqlSessionFactory =
                                new SqlSessionFactoryBuilder().build(inputStream);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //不用手动提交sqlSession.commit();
        return sqlSessionFactory.openSession(true);
    }
}
