package com.codecool.codecoin.model;

import java.math.BigDecimal;

public class Transaction {
    private Long id;
    private Long userId;
    private TransactionType type;
    private Cryptocurrency crypto;
    private BigDecimal cryptoAmount;
    private CurrencyType currency;
    private BigDecimal currencyAmount;
}
