package com.walter.dao;

import com.walter.bean.User;
import java.util.List;

public interface UserMapper {
    int insert(User record);

    List<User> selectAll();
}