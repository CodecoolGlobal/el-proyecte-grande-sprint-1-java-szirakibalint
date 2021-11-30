package com.codecool.codecoin.controller;

import com.codecool.codecoin.dao.CryptocurrencyDAO;
import com.codecool.codecoin.model.Cryptocurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class APIController {
    private CryptocurrencyDAO cryptocurrencyDAO;

    @Autowired
    public APIController(CryptocurrencyDAO cryptocurrencyDAO) {
        this.cryptocurrencyDAO = cryptocurrencyDAO;
    }

    @GetMapping("/coins")
    public Set<Cryptocurrency> getAll() {
        return cryptocurrencyDAO.getAll();
    }

    @GetMapping("/coins/{id}")
    public Cryptocurrency getCurrencyById(@PathVariable String id) {
        return cryptocurrencyDAO.getCurrencyById(id);
    }

    @PostMapping("/coins/{id}")
    public String buyCurrency(@PathVariable String id, @RequestParam int amount) {
        return "Bought " + String.valueOf(amount) + " of " + id;
    }

    @PutMapping("/coins/{id}")
    public String sellCurrency(@PathVariable String id, @RequestParam int amount) {
        return "Sold " + String.valueOf(amount) + " of " + id;
    }
}
