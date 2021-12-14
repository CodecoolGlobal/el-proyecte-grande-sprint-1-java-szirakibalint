package com.codecool.codecoin.dao;

import com.codecool.codecoin.model.Portfolio;

import java.math.BigDecimal;

public interface UserDAO {

    Portfolio getPortfolio();
    String buyCrypto(String id, BigDecimal amount);
    String sellCrypto(String id, BigDecimal amount);
}
