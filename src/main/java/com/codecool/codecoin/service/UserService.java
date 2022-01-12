package com.codecool.codecoin.service;

import com.codecool.codecoin.dao.CryptocurrencyDAO;
import com.codecool.codecoin.dao.UserDAO;
import com.codecool.codecoin.model.Cryptocurrency;
import com.codecool.codecoin.model.Portfolio;
import com.codecool.codecoin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class UserService {
    private final UserDAO userDAO;
    private final CryptocurrencyDAO cryptocurrencyDAO;

    @Autowired
    public UserService(UserDAO userDAO, CryptocurrencyDAO cryptocurrencyDAO) {
        this.userDAO = userDAO;
        this.cryptocurrencyDAO = cryptocurrencyDAO;
    }

    public User findById(Long id) {
        return userDAO.findById(id);
    }

    public void save(User user) {
        userDAO.save(user);
    }

    public BigDecimal calculateTotalBalance(User user) {
        Portfolio portfolio = user.getPortfolio();
        BigDecimal userBalance = user.getCurrencyBalance();
        Map<Cryptocurrency, BigDecimal> cryptocurrencies = portfolio.getCryptoCurrencies();
        BigDecimal totalBalance = userBalance;
        for (Map.Entry<Cryptocurrency, BigDecimal> entry : cryptocurrencies.entrySet()) {
            Cryptocurrency cryptocurrencyActualData = cryptocurrencyDAO.getCurrencyById(entry.getKey().getId());
            BigDecimal value = cryptocurrencyActualData.getCurrentPrice().multiply(entry.getValue());
            totalBalance = totalBalance.add(value);
        }
        return totalBalance;
    }
}
