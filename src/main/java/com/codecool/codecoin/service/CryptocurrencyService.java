package com.codecool.codecoin.service;

import com.codecool.codecoin.dao.CryptocurrencyDAO;
import com.codecool.codecoin.model.Cryptocurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CryptocurrencyService {

    private final CryptocurrencyDAO cryptocurrencyDAO;

    @Autowired
    public CryptocurrencyService(CryptocurrencyDAO cryptocurrencyDAO) {
        this.cryptocurrencyDAO = cryptocurrencyDAO;
    }

    public Set<Cryptocurrency> getAll() {
        return cryptocurrencyDAO.getAll();
    }

    public Cryptocurrency getCurrencyById(String id) {
        return cryptocurrencyDAO.getCurrencyById(id);
    }
}
