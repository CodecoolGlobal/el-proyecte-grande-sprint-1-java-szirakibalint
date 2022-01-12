package com.codecool.codecoin.model;

import lombok.AccessLevel;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Getter
public class User {
    private Long id;
    private String username;
    @Getter(AccessLevel.NONE)
    private String password;
    private Portfolio portfolio;
    private CurrencyType preferredCurrency;
    private BigDecimal currencyBalance;
    private List<Transaction> transactions;

    public void recordTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
