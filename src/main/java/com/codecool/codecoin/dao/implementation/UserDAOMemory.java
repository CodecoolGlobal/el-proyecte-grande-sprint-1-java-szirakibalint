package com.codecool.codecoin.dao.implementation;

import com.codecool.codecoin.dao.CryptocurrencyDAO;
import com.codecool.codecoin.dao.UserDAO;
import com.codecool.codecoin.model.Cryptocurrency;
import com.codecool.codecoin.model.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserDAOMemory implements UserDAO {

    private final Portfolio portfolio;
    private final CryptocurrencyDAO cryptocurrencyDAO;

    @Autowired
    public UserDAOMemory(Portfolio portfolio, CryptocurrencyDAO cryptocurrencyDAO) {
        this.portfolio = portfolio;
        this.cryptocurrencyDAO = cryptocurrencyDAO;
    }

    @Override
    public Portfolio getPortfolio() {
        return portfolio;
    }

    @Override
    public String buyCrypto(String id, BigDecimal amount) {
        if (amount != null && amount.compareTo(BigDecimal.ZERO) > 0) {
            Cryptocurrency cryptocurrency = cryptocurrencyDAO.getCurrencyById(id);
            if (cryptocurrency == null) {
                return "Invalid id";
            }
            if (portfolio.buyCrypto(cryptocurrency, amount)) {
                return "Bought " + amount + " of " + cryptocurrency.getName();
            } else {
                return "Transaction to buy currency failed";
            }
        }
        return "Zero or negative amount of crypto";
    }

    @Override
    public String sellCrypto(String id, BigDecimal amount) {
        if (amount != null && amount.compareTo(BigDecimal.ZERO) > 0) {
            Cryptocurrency cryptocurrency = cryptocurrencyDAO.getCurrencyById(id);
            if (cryptocurrency == null) {
                return "Invalid id";
            }
            if (portfolio.sellCrypto(cryptocurrency, amount)) {
                return "Sold " + amount + " of " + cryptocurrency.getName();
            } else {
                return "Not enough " + cryptocurrency.getName();
            }
        }
        return "Zero or negative amount of crypto";
    }
}
