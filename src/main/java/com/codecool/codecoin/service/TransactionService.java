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

    private void handleTransaction(Transaction transaction) {
        User user = userService.findById(transaction.getUserId());
        boolean transactionSuccess = initTransaction(user, transaction);
        if (transactionSuccess) {
            recordTransaction(user, transaction);
        }
    }

    private boolean initTransaction(User user, Transaction transaction) {
        return false;
    }

    private boolean initBuy(Portfolio portfolio, Transaction transaction) {
        return false;
    }

    private boolean initSell(Portfolio portfolio, Transaction transaction) {
        return false;
    }

    private void recordTransaction(User user, Transaction transaction) {

    }
}
