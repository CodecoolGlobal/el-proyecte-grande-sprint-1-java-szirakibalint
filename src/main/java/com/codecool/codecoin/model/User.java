package com.codecool.codecoin.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    @Getter(AccessLevel.NONE)
    private String password;
    @OneToOne
    private Portfolio portfolio;
    @Enumerated(EnumType.STRING)
    private CurrencyType preferredCurrency;
    @Setter
    private BigDecimal currencyBalance;
    @OneToMany
    private List<Transaction> transactions;

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.portfolio = new Portfolio();
        this.transactions = new ArrayList<>();
        this.preferredCurrency = CurrencyType.USD;
        this.currencyBalance = BigDecimal.valueOf(20000);
    }

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
