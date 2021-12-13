package com.codecool.codecoin.service;

import com.codecool.codecoin.dao.CryptocurrencyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CryptocurrencyService {

    private final CryptocurrencyDAO cryptocurrencyDAO;

    @Autowired
    public CryptocurrencyService(CryptocurrencyDAO cryptocurrencyDAO) {
        this.cryptocurrencyDAO = cryptocurrencyDAO;
    }
}
