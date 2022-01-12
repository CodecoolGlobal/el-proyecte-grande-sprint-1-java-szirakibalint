package com.codecool.codecoin.model;

import java.math.BigDecimal;
import java.util.List;

public class User {
    private Long id;
    private String username;
    private String password;
    private Portfolio portfolio;
    private CurrencyType preferredCurrency;
    private BigDecimal currencyBalance;
    private List<Transaction> transactions;
}
