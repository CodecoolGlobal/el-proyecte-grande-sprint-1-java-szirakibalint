package com.codecool.codecoin.dao;

import com.codecool.codecoin.model.Cryptocurrency;

import java.util.Set;

public interface CryptocurrencyDAO {
    Set<Cryptocurrency> getAll();
    Cryptocurrency get(String id);
}
