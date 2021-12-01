package com.codecool.codecoin.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private final Map<Cryptocurrency, BigDecimal> cryptoCurrencies;
    private final Map<CurrencyType, BigDecimal> currencies;
    private static Portfolio instance;

    private Portfolio() {
        cryptoCurrencies = new HashMap<>();
        currencies = new HashMap<>(){{
            put(CurrencyType.USD, BigDecimal.valueOf(20000));
        }};
    }

    public static Portfolio getInstance() {
        if (instance == null) {
            instance = new Portfolio();
        }
        return instance;
    }

    /**
     * Get {@link Cryptocurrency} balances from Portfolio.
     * @return cryptocurrencies and amounts as key-value pairs in a Map
     */
    public Map<Cryptocurrency, BigDecimal> getCryptoCurrencies() {
        return cryptoCurrencies;
    }

    /**
     * Get currency ({@link CurrencyType}) balances from Portfolio.
     * @return currency types and amounts as key-value pairs in a Map.
     */
    public Map<CurrencyType, BigDecimal> getCurrencies() {
        return currencies;
    }

    /**
     * Buy {@link Cryptocurrency} in the specified amount.
     * @param cryptoCurrency the type of {@link Cryptocurrency}
     * @param amount the decimal amount of {@link Cryptocurrency}
     * @return the outcome of the transaction as a boolean value
     */
    public boolean buyCrypto(Cryptocurrency cryptoCurrency, BigDecimal amount) {
        BigDecimal cost = cryptoCurrency.getCurrentPrice().multiply(amount);
        if (currencies.get(CurrencyType.USD).compareTo(cost) > -1) {
            currencies.put(CurrencyType.USD, currencies.get(CurrencyType.USD).subtract(cost));
            if (!cryptoCurrencies.containsKey(cryptoCurrency)) {
                cryptoCurrencies.put(cryptoCurrency, amount);
            } else {
                cryptoCurrencies.put(cryptoCurrency, cryptoCurrencies.get(cryptoCurrency).add(amount));
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sell {@link Cryptocurrency} in the specified amount.
     * @param cryptoCurrency the type of {@link Cryptocurrency}
     * @param amount the decimal amount of {@link Cryptocurrency}
     * @return the outcome of the transaction as a boolean value
     */
    public boolean sellCrypto(Cryptocurrency cryptoCurrency, BigDecimal amount) {
        BigDecimal value = cryptoCurrency.getCurrentPrice().multiply(amount);
        if (cryptoCurrencies.containsKey(cryptoCurrency) && cryptoCurrencies.get(cryptoCurrency).compareTo(amount) > -1) {
            cryptoCurrencies.put(cryptoCurrency, cryptoCurrencies.get(cryptoCurrency).subtract(amount));
            currencies.put(CurrencyType.USD, currencies.get(CurrencyType.USD).add(value));
            return true;
        }
        return false;
    }
}
