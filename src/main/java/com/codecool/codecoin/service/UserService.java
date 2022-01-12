package com.codecool.codecoin.service;

import com.codecool.codecoin.dao.NewUserDAO;
import com.codecool.codecoin.dao.UserDAO;
import com.codecool.codecoin.model.Portfolio;
import com.codecool.codecoin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final NewUserDAO newUserDAO;
    private final Calculator calculator;

    @Autowired
    public UserService(UserDAO userDAO, NewUserDAO newUserDAO, Calculator calculator) {
        this.userDAO = userDAO;
        this.newUserDAO = newUserDAO;
        this.calculator = calculator;
    }

    public User findById(Long id) {
        return newUserDAO.findById(id);
    }

    public void save(User user) {
        newUserDAO.save(user);
    }

    public Portfolio getPortfolio() {
        return userDAO.getPortfolio();
    }

    public String buyCryptocurrency(String id, BigDecimal amount) {
        return userDAO.buyCrypto(id, amount);
    }

    public String sellCryptocurrency(String id, BigDecimal amount) {
        return userDAO.sellCrypto(id, amount);
    }

    public BigDecimal getTotalBalance() {
        return calculator.calculateTotalBalance();
    }
}
