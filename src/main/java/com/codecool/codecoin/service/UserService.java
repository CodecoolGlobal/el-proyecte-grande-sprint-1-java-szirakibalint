package com.codecool.codecoin.service;

import com.codecool.codecoin.dao.UserDAO;
import com.codecool.codecoin.model.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final Calculator calculator;

    @Autowired
    public UserService(UserDAO userDAO, Calculator calculator) {
        this.userDAO = userDAO;
        this.calculator = calculator;
    }

    public Portfolio getPortfolio() {
        return userDAO.getPortfolio();
    }

    public String buyCryptoCurrency(String id, BigDecimal amount) {
        return userDAO.buyCrypto(id, amount);
    }

    public String sellCryptocurrency(String id, BigDecimal amount) {
        return userDAO.sellCrypto(id, amount);
    }

    public BigDecimal getTotalBalance() {
        return calculator.calculateTotalBalance(userDAO.getPortfolio());
    }
}
