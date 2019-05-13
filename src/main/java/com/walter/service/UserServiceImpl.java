package com.walter.service;


import com.walter.bean.User;
import com.walter.dao.UserDao;
import com.walter.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public void insert(User user) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        sqlSession.insert("insert", user);
    }

    @Override
    public List<User> select() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.select();
        sqlSession.close();
        return userList;
    }
}


class test {

    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        User user = new User();
        user.setUsername("walter");
        user.setPassword("123");
        System.out.println(user.getId());
        userService.insert(user);
        System.out.println(user.getId());

        List<User> userList = userService.select();
        System.out.println(userList);
    }
}