package com.codecool.codecoin.service;

import com.codecool.codecoin.controller.APIController;
import com.codecool.codecoin.model.Cryptocurrency;
import com.codecool.codecoin.model.CurrencyType;
import com.codecool.codecoin.model.Portfolio;

import java.math.BigDecimal;
import java.util.Map;

public class Calculator {

    public BigDecimal calculateTotalBalance(Portfolio portfolio, APIController apiController) {
        Map<CurrencyType, BigDecimal> currencies = portfolio.getCurrencies();
        Map<Cryptocurrency, BigDecimal> cryptocurrencies = portfolio.getCryptoCurrencies();
        BigDecimal totalBalance = currencies.get(CurrencyType.USD);
        for (Map.Entry<Cryptocurrency, BigDecimal> entry : cryptocurrencies.entrySet()) {
            Cryptocurrency cryptocurrencyActualData = apiController.getCurrencyById(entry.getKey().getId());
            BigDecimal value = cryptocurrencyActualData.getCurrentPrice().multiply(entry.getValue());
            totalBalance = totalBalance.add(value);
        }
        return totalBalance;
    }
}
