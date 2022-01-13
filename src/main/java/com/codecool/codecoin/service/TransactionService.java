package com.codecool.codecoin.service;

import com.codecool.codecoin.model.Transaction;
import com.codecool.codecoin.model.User;
import com.codecool.codecoin.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.codecool.codecoin.model.TransactionType.BUY;

@Service
public class TransactionService {
    private final UserService userService;
    private final PortfolioService portfolioService;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(UserService userService, PortfolioService portfolioService, TransactionRepository transactionRepository) {
        this.userService = userService;
        this.portfolioService = portfolioService;
        this.transactionRepository = transactionRepository;
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
        if (transaction.getType() == BUY) {
            return initBuy(user, transaction);
        } else {
            return initSell(user, transaction);
        }
    }

    private boolean initBuy(User user, Transaction transaction) {
        return portfolioService.buyCrypto(user, transaction);
    }

    private boolean initSell(User user, Transaction transaction) {
        return portfolioService.sellCrypto(user, transaction);
    }

    private void recordTransaction(User user, Transaction transaction) {
        user.recordTransaction(transaction);
        transactionRepository.save(transaction);
        userService.save(user);
    }
}
