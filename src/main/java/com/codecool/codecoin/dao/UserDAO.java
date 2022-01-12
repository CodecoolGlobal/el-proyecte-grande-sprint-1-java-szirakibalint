package com.codecool.codecoin.dao;

import com.codecool.codecoin.model.User;

public interface UserDAO {
    User findById(Long id);
    void save(User user);
}
