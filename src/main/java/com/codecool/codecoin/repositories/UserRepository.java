package com.codecool.codecoin.repositories;

import com.codecool.codecoin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user.username FROM RegularUser user")
    List<String> findUsernames();

    User findByUsername(String username);
}
