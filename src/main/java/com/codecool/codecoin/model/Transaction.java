package com.codecool.codecoin.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Transaction {
    private Long id;
    private Long userId;
    private LocalDateTime date;
    private TransactionType type;
    private String cryptoId;
    private Cryptocurrency crypto;
    private BigDecimal cryptoAmount;
    private CurrencyType currency;
    private BigDecimal currencyAmount;
}
