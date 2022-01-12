package com.codecool.codecoin.service;

import com.codecool.codecoin.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.codecool.codecoin.model.TransactionType.BUY;

@Service
public class TransactionService {
    private final UserService userService;

    @Autowired
    public TransactionService(UserService userService) {
        this.userService = userService;
    }

    public boolean handleTransaction(Transaction transaction) {
        User user = userService.findById(transaction.getUserId());
        boolean transactionSuccess = initTransaction(user, transaction);
        if (transactionSuccess) {
            recordTransaction(user, transaction);
            return true;
        }
        return false;
    }

    private boolean initTransaction(User user, Transaction transaction) {
        Portfolio portfolio = user.getPortfolio();
        if (transaction.getType() == BUY) {
            return initBuy(portfolio, transaction);
        } else {
            return initSell(portfolio, transaction);
        }
    }

    private boolean initBuy(Portfolio portfolio, Transaction transaction) {
        Cryptocurrency crypto = transaction.getCrypto();
        BigDecimal amount = transaction.getCryptoAmount();
        return portfolio.sellCrypto(crypto, amount);
    }

    private boolean initSell(Portfolio portfolio, Transaction transaction) {
        Cryptocurrency crypto = transaction.getCrypto();
        BigDecimal amount = transaction.getCryptoAmount();
        return portfolio.buyCrypto(crypto, amount);
    }

    private void recordTransaction(User user, Transaction transaction) {
        user.recordTransaction(transaction);
        userService.save(user);
    }
}
