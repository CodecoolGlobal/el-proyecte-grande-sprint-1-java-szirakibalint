package com.codecool.codecoin.dao.implementation;

import com.codecool.codecoin.dao.UserDAO;
import com.codecool.codecoin.model.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDAOMemory implements UserDAO {

    private final Portfolio portfolio;

    @Autowired
    public UserDAOMemory(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public Portfolio getPortfolio() {
        return portfolio;
    }
}
