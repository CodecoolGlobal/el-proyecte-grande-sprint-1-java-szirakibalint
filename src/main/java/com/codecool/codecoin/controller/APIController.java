package com.codecool.codecoin.controller;

import com.codecool.codecoin.dao.CryptocurrencyDAO;
import com.codecool.codecoin.model.Cryptocurrency;
import com.codecool.codecoin.model.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;

/**
 * A class for API endpoint methods.
 */
@RestController
@RequestMapping("/api")
public class APIController {
    private final CryptocurrencyDAO cryptocurrencyDAO;
    private final Portfolio portfolio;

    /**
     * Creates an {@link APIController} instance.
     * @param cryptocurrencyDAO used for accessing {@link Cryptocurrency} data
     * @param portfolio used for handling buy and sell logic
     */
    @Autowired
    public APIController(CryptocurrencyDAO cryptocurrencyDAO, Portfolio portfolio) {
        this.cryptocurrencyDAO = cryptocurrencyDAO;
        this.portfolio = portfolio;
    }

    /** Fetches all {@link Cryptocurrency} using the {@link CryptocurrencyDAO}.
     * @return Set of all {@link Cryptocurrency}
     */
    @GetMapping("/coins")
    public Set<Cryptocurrency> getAll() {
        return cryptocurrencyDAO.getAll();
    }

    /**
     * Fetches a {@link Cryptocurrency} using the {@link CryptocurrencyDAO}.
     * @param id an identifier string for a {@link Cryptocurrency} (e.g. "bitcoin")
     * @return Cryptocurrency object if a match is found, else null.
     */
    @GetMapping("/coins/{id}")
    public Cryptocurrency getCurrencyById(@PathVariable String id) {
        return cryptocurrencyDAO.getCurrencyById(id);
    }

    @PostMapping("/coins/{id}")
    public String buyCurrency(@PathVariable String id, @RequestParam BigDecimal amount) {
        Cryptocurrency cryptocurrency = getCurrencyById(id);
        if (cryptocurrency == null) {
            return "Invalid id";
        }
        if (portfolio.buyCrypto(cryptocurrency, amount)) {
            return "Bought " + amount + " of " + cryptocurrency.getName();
        } else {
            return "Transaction to buy currency failed";
        }
    }

    @PutMapping("/coins/{id}")
    public String sellCurrency(@PathVariable String id, @RequestParam BigDecimal amount) {
        Cryptocurrency cryptocurrency = getCurrencyById(id);
        if (cryptocurrency == null) {
            return "Invalid id";
        }
        if (portfolio.sellCrypto(cryptocurrency, amount)) {
            return "Sold " + amount + " of " + cryptocurrency.getName();
        } else {
            return "Not enough " + cryptocurrency.getName();
        }
    }

    @GetMapping("/portfolio")
    public Portfolio getPortfolio() {
        return portfolio;
    }
}
