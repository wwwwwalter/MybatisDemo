package com.walter.service;

import com.walter.bean.User;

import java.util.List;

public interface UserService {
    void insert(User user);

    List<User> select();
}
