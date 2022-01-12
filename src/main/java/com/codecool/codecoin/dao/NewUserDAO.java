package com.codecool.codecoin.dao;

import com.codecool.codecoin.model.User;

public interface NewUserDAO {
    User findById(Long id);
    void save(User user);
}
