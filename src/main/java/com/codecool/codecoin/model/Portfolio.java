package com.codecool.codecoin.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private final Map<Cryptocurrency, BigDecimal> currencies;
    private static Portfolio instance;

    private Portfolio() {
        currencies = new HashMap<>();
    }

    public static Portfolio getInstance() {
        if (instance == null) {
            instance = new Portfolio();
        }
        return instance;
    }

    public Map<Cryptocurrency, BigDecimal> getCurrencies() {
        return currencies;
    }

    public boolean buyCrypto(Cryptocurrency currency, BigDecimal amount) {
        if (!currencies.containsKey(currency)) {
            currencies.put(currency, amount);
        } else {
            currencies.put(currency, currencies.get(currency).add(amount));
        }
        return true;
    }

    public boolean sellCrypto(Cryptocurrency currency, BigDecimal amount) {
        if (currencies.containsKey(currency) && currencies.get(currency).compareTo(amount) > -1) {
            currencies.put(currency, currencies.get(currency).subtract(amount));
            return true;
        }
        return false;
    }
}
