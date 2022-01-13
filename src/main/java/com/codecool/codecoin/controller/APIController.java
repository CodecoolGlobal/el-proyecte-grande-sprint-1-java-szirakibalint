package com.codecool.codecoin.controller;

import com.codecool.codecoin.dao.CryptocurrencyDAO;
import com.codecool.codecoin.model.Cryptocurrency;
import com.codecool.codecoin.model.Portfolio;
import com.codecool.codecoin.model.Transaction;
import com.codecool.codecoin.model.User;
import com.codecool.codecoin.service.CryptocurrencyService;
import com.codecool.codecoin.service.TransactionService;
import com.codecool.codecoin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A class for API endpoint methods.
 */
@RestController
@RequestMapping("/api")
public class APIController {
    private final CryptocurrencyService cryptocurrencyService;
    private final TransactionService transactionService;
    private final UserService userService;

    /**
     * Creates an {@link APIController} instance.
     * @param cryptocurrencyService used for accessing {@link Cryptocurrency} data
     * @param transactionService used for handling buy and sell logic
     * @param userService used for user operations
     */
    @Autowired
    public APIController(CryptocurrencyService cryptocurrencyService, TransactionService transactionService, UserService userService) {
        this.cryptocurrencyService = cryptocurrencyService;
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        userService.save(user);
        return userService.findById(user.getId());
    }

    /** Fetches all {@link Cryptocurrency} using the {@link CryptocurrencyDAO}.
     * @return Set of all {@link Cryptocurrency}
     */
    @GetMapping("/coins")
    public Set<Cryptocurrency> getAll() {
        return cryptocurrencyService.getAll();
    }

    /**
     * Fetches a {@link Cryptocurrency} using the {@link CryptocurrencyDAO}.
     * @param id an identifier string for a {@link Cryptocurrency} (e.g. "bitcoin")
     * @return Cryptocurrency object if a match is found, else null.
     */
    @GetMapping("/coins/{id}")
    public Cryptocurrency getCurrencyById(@PathVariable String id) {
        return cryptocurrencyService.getCurrencyById(id);
    }

    /**
     * Buy a {@link Cryptocurrency} using the {@link TransactionService}.
     * @return the outcome of the transaction as a string
     */
    @PostMapping("/coins")
    public String buyCurrency(@RequestBody Transaction transaction) {
        return transactionService.handleTransaction(transaction) ? "success" : "fail";
    }

    /**
     * Sell a {@link Cryptocurrency} using the {@link TransactionService}.
     * @return the outcome of the transaction as a string
     */
    @PutMapping("/coins")
    public String sellCurrency(@RequestBody Transaction transaction) {
        return transactionService.handleTransaction(transaction) ? "success" : "fail";
    }

    /**
     * Get the portfolio information
     * @return portfolio information in key-value pairs
     */
    @GetMapping("/portfolio/{userId}")
    public Map<String, Object> getPortfolio(@PathVariable Long userId) {
        User user = userService.findById(userId);
        return new HashMap<>(){{
            put("portfolio", user.getPortfolio());
            put("currencyBalance", user.getCurrencyBalance());
            put("totalBalance", userService.calculateTotalBalance(user));
        }};
    }
}
