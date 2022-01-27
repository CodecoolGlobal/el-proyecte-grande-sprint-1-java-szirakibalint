package com.codecool.codecoin.service;

import com.codecool.codecoin.dao.CryptocurrencyDAO;
import com.codecool.codecoin.model.Cryptocurrency;
import com.codecool.codecoin.model.Portfolio;
import com.codecool.codecoin.model.User;
import com.codecool.codecoin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CryptocurrencyDAO cryptocurrencyDAO;

    @Autowired
    public UserService(UserRepository userRepository, CryptocurrencyDAO cryptocurrencyDAO) {
        this.userRepository = userRepository;
        this.cryptocurrencyDAO = cryptocurrencyDAO;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<String> findAllUsernames() {
        return userRepository.findUsernames();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public BigDecimal calculateTotalBalance(User user) {
        Portfolio portfolio = user.getPortfolio();
        BigDecimal userBalance = user.getCurrencyBalance();
        Map<String, BigDecimal> cryptocurrencies = portfolio.getCryptoCurrencies();
        BigDecimal totalBalance = userBalance;
        for (Map.Entry<String, BigDecimal> entry : cryptocurrencies.entrySet()) {
            Cryptocurrency cryptocurrencyActualData = cryptocurrencyDAO.getCurrencyById(entry.getKey());
            BigDecimal value = cryptocurrencyActualData.getCurrentPrice().multiply(entry.getValue());
            totalBalance = totalBalance.add(value);
        }
        return totalBalance;
    }
}
