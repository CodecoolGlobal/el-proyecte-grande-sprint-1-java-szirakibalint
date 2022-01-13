package com.codecool.codecoin.service;

import com.codecool.codecoin.model.Cryptocurrency;
import com.codecool.codecoin.model.CurrencyType;
import com.codecool.codecoin.model.Transaction;
import com.codecool.codecoin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class PortfolioService {
    private final CryptocurrencyService cryptocurrencyService;

    @Autowired
    public PortfolioService(CryptocurrencyService cryptocurrencyService) {
        this.cryptocurrencyService = cryptocurrencyService;
    }

    /**
     * Buy {@link Cryptocurrency} in the specified amount.
     * @param user the user initiating the {@link Transaction}
     * @param transaction the transaction being initiated
     * @return the outcome of the transaction as a boolean value
     */
    public boolean buyCrypto(User user, Transaction transaction) {
        Cryptocurrency cryptocurrency = cryptocurrencyService.getCurrencyById(transaction.getCryptoId());
        BigDecimal amount = transaction.getCryptoAmount();
        BigDecimal cost = cryptocurrency.getCurrentPrice().multiply(amount);
        BigDecimal userBalance = user.getCurrencyBalance();
        Map<String, BigDecimal> cryptoCurrencies = user.getPortfolio().getCryptoCurrencies();
        if (amount.compareTo(BigDecimal.ZERO) > 0 && userBalance.compareTo(cost) > -1) {
            user.setCurrencyBalance(userBalance.subtract(cost));
            if (!cryptoCurrencies.containsKey(cryptocurrency.getId())) {
                cryptoCurrencies.put(cryptocurrency.getId(), amount);
            } else {
                cryptoCurrencies.put(cryptocurrency.getId(), cryptoCurrencies.get(cryptocurrency.getId()).add(amount));
            }
            //transaction.setCrypto(cryptocurrency);
            transaction.setCurrencyAmount(cost);
            transaction.setDate(LocalDateTime.now());
            transaction.setCurrency(CurrencyType.USD);
            return true;
        } else {
            return false;
        }
    }



    /**
     * Sell {@link Cryptocurrency} in the specified amount.
     * @param user the user initiating the {@link Transaction}
     * @param transaction the transaction being initiated
     * @return the outcome of the transaction as a boolean value
     */
    public boolean sellCrypto(User user, Transaction transaction) {
        Cryptocurrency cryptocurrency = cryptocurrencyService.getCurrencyById(transaction.getCryptoId());
        BigDecimal amount = transaction.getCryptoAmount();
        BigDecimal value = cryptocurrency.getCurrentPrice().multiply(amount);
        Map<String, BigDecimal> cryptoCurrencies = user.getPortfolio().getCryptoCurrencies();
        if (amount.compareTo(BigDecimal.ZERO) > 0 && cryptoCurrencies.containsKey(cryptocurrency.getId()) && cryptoCurrencies.get(cryptocurrency.getId()).compareTo(amount) > -1) {
            cryptoCurrencies.put(cryptocurrency.getId(), cryptoCurrencies.get(cryptocurrency.getId()).subtract(amount));
            BigDecimal userBalance = user.getCurrencyBalance();
            user.setCurrencyBalance(userBalance.add(value));
            //transaction.setCrypto(cryptocurrency);
            transaction.setCurrencyAmount(value);
            transaction.setDate(LocalDateTime.now());
            transaction.setCurrency(CurrencyType.USD);
            return true;
        }
        return false;
    }
}
