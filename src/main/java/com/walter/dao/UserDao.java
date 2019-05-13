package com.walter.dao;


import com.walter.bean.User;

import java.util.List;

public interface UserDao {
    void insert(User user);

    List<User> select();

    User selectById(int id);
}
