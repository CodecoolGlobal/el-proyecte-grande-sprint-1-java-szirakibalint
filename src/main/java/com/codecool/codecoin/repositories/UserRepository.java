package com.codecool.codecoin.repositories;

import com.codecool.codecoin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
