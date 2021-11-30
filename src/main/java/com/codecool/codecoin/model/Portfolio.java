package com.codecool.codecoin.model;

import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private final Map<Cryptocurrency, Integer> currencies;
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

    public Map<Cryptocurrency, Integer> getCurrencies() {
        return currencies;
    }

    public boolean buyCrypto(Cryptocurrency currency, int amount) {
        if (!currencies.containsKey(currency)) {
            currencies.put(currency, amount);
        } else {
            currencies.put(currency, currencies.get(currency) + amount);
        }
        return true;
    }

    public boolean sellCrypto(Cryptocurrency currency, int amount) {
        if (currencies.containsKey(currency) && currencies.get(currency) >= amount) {
            currencies.put(currency, currencies.get(currency) - amount);
            return true;
        }
        return false;
    }
}
