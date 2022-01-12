package com.codecool.codecoin.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private final Map<Cryptocurrency, BigDecimal> cryptoCurrencies;

    public Portfolio() {
        cryptoCurrencies = new HashMap<>();
    }

    /**
     * Get {@link Cryptocurrency} balances from Portfolio.
     * @return cryptocurrencies and amounts as key-value pairs in a Map
     */
    public Map<Cryptocurrency, BigDecimal> getCryptoCurrencies() {
        return cryptoCurrencies;
    }
}
