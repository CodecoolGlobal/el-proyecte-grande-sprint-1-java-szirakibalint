package com.codecool.codecoin.dao.implementation;

import com.codecool.codecoin.dao.UserDAO;
import com.codecool.codecoin.model.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDAOMem implements UserDAO {
    private final Set<User> users;

    public UserDAOMem() {
        this.users = new HashSet<>();
        User user = new User(0L,"username", "password");
        users.add(user);

    }

    @Override
    public User findById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void save(User user) {
        users.add(user);
    }
}
