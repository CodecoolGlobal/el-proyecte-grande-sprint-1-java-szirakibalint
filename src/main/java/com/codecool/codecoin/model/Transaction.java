package com.codecool.codecoin.model;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Transaction {
    private Long id;
    private Long userId;
    private TransactionType type;
    private Cryptocurrency crypto;
    private BigDecimal cryptoAmount;
    private CurrencyType currency;
    private BigDecimal currencyAmount;
}
