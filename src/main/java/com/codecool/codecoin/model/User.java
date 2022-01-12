package com.codecool.codecoin.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String username;
    private String password;
    private Portfolio portfolio;
    private CurrencyType preferredCurrency;
    private BigDecimal currencyBalance;
    private List<Transaction> transactions;

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
