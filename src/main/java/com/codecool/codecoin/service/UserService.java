package com.codecool.codecoin.service;

import com.codecool.codecoin.dao.UserDAO;
import com.codecool.codecoin.model.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
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
}
