package com.codecool.codecoin.dao;

import com.codecool.codecoin.model.Cryptocurrency;

import java.util.Set;

/**
 * Contains method signatures for basic Cryptocurrency data access operations.
 */
public interface CryptocurrencyDAO {
    Set<Cryptocurrency> getAll();
    Cryptocurrency getCurrencyById(String id);
}
