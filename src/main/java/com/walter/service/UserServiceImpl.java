package com.walter.service;


import com.walter.bean.User;
import com.walter.dao.UserDao;
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

    @Override
    public void insert(User user) {
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        sqlSession.insert("insert", user);
    }

    @Override
    public List<User> select() {
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.select();
        return userList;
    }

    @Override
    public User selectById(int id) {
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        return userDao.selectById(id);
    }
}


class test {

    public static void main(String[] args) throws InterruptedException {

        UserServiceImpl userService = new UserServiceImpl();

        /*User user = new User();
        user.setUsername("walter");
        user.setPassword("123");
        System.out.println(user.getId());
        userService.insert(user);
        System.out.println(user.getId());

        List<User> userList = userService.select();
        System.out.println(userList);*/

        User user = userService.selectById(1);
        System.out.println(user);

        Thread.sleep(5000);
        //如果这个空挡别的sqlsession修改了数据库，这里是不知道的
        user = userService.selectById(1);
        System.out.println(user);
    }
}