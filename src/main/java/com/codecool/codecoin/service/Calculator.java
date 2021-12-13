package com.codecool.codecoin.service;

import com.codecool.codecoin.controller.APIController;
import com.codecool.codecoin.dao.CryptocurrencyDAO;
import com.codecool.codecoin.model.Cryptocurrency;
import com.codecool.codecoin.model.CurrencyType;
import com.codecool.codecoin.model.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class Calculator {

    private final CryptocurrencyDAO cryptocurrencyDAO;

    @Autowired
    public Calculator(CryptocurrencyDAO cryptocurrencyDAO) {
        this.cryptocurrencyDAO = cryptocurrencyDAO;
    }

    public BigDecimal calculateTotalBalance(Portfolio portfolio) {
        Map<CurrencyType, BigDecimal> currencies = portfolio.getCurrencies();
        Map<Cryptocurrency, BigDecimal> cryptocurrencies = portfolio.getCryptoCurrencies();
        BigDecimal totalBalance = currencies.get(CurrencyType.USD);
        for (Map.Entry<Cryptocurrency, BigDecimal> entry : cryptocurrencies.entrySet()) {
            Cryptocurrency cryptocurrencyActualData = cryptocurrencyDAO.getCurrencyById(entry.getKey().getId());
            BigDecimal value = cryptocurrencyActualData.getCurrentPrice().multiply(entry.getValue());
            totalBalance = totalBalance.add(value);
        }
        return totalBalance;
    }
}
