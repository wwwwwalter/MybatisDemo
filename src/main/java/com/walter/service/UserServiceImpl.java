package com.walter.service;


import com.walter.bean.User;
import com.walter.dao.UserMapper;
import com.walter.pool.MyDataSourceFactory;
import com.walter.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static SqlSession sqlSession;

    static {
        sqlSession = MyBatisUtil.getSqlSession();
        //最后记得关闭
        //sqlSession.close();
    }

    public void closeSqlSession() {
        sqlSession.close();
    }

    @Override
    public void insert(User user) {
        UserMapper UserMapper = sqlSession.getMapper(UserMapper.class);
        sqlSession.insert("insert", user);
    }

    @Override
    public List<User> selectAll() {
        UserMapper UserMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = UserMapper.selectAll();
        return userList;
    }


}


class test {

    public static void main(String[] args) throws InterruptedException {

        UserServiceImpl userService = new UserServiceImpl();

        MyDataSourceFactory.dataSourceStatus();

        User user = new User();
        user.setName("walter");
        user.setPassword("123");
        System.out.println(user.getId());
        userService.insert(user);
        System.out.println(user.getId());

        List<User> userList = userService.selectAll();
        System.out.println(userList);


        userService.closeSqlSession();
        MyDataSourceFactory.dataSourceStatus();
    }
}