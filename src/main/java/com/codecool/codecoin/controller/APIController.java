package com.codecool.codecoin.controller;

import com.codecool.codecoin.dao.CryptocurrencyDAO;
import com.codecool.codecoin.model.Cryptocurrency;
import com.codecool.codecoin.model.Portfolio;
import com.codecool.codecoin.service.CryptocurrencyService;
import com.codecool.codecoin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * A class for API endpoint methods.
 */
@RestController
@RequestMapping("/api")
public class APIController {
    private final CryptocurrencyService cryptocurrencyService;
    private final UserService userService;

    /**
     * Creates an {@link APIController} instance.
     * @param cryptocurrencyService used for accessing {@link Cryptocurrency} data
     * @param userService used for handling buy and sell logic
     */
    @Autowired
    public APIController(CryptocurrencyService cryptocurrencyService, UserService userService) {
        this.cryptocurrencyService = cryptocurrencyService;
        this.userService = userService;
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
     * Buy a {@link Cryptocurrency} using the {@link Portfolio}.
     * @param id an identifier string for a {@link Cryptocurrency} (e.g. "bitcoin")
     * @param data contains the decimal amount of {@link Cryptocurrency} to purchase
     * @return the outcome of the transaction as a string
     */
    @PostMapping("/coins/{id}")
    public String buyCurrency(@PathVariable String id, @RequestBody Map<String, BigDecimal> data) {
        BigDecimal amount = data.get("amount");
        return userService.buyCryptoCurrency(id, amount);
    }

    /**
     * Sell a {@link Cryptocurrency} using the {@link Portfolio}.
     * @param id an identifier string for a {@link Cryptocurrency} (e.g. "bitcoin")
     * @param data contains the decimal amount of {@link Cryptocurrency} to purchase
     * @return the outcome of the transaction as a string
     */
    @PutMapping("/coins/{id}")
    public String sellCurrency(@PathVariable String id, @RequestBody Map<String, BigDecimal> data) {
        BigDecimal amount = data.get("amount");
        return userService.sellCryptocurrency(id, amount);
    }

    /**
     * Get the {@link Portfolio} instance.
     * @return Portfolio
     */
    @GetMapping("/portfolio")
    public Portfolio getUserService() {
        return userService.getPortfolio();
    }
}
