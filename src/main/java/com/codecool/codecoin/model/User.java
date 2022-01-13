package com.codecool.codecoin.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Entity(name = "RegularUser")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "id",
            nullable = false
    )
    private Long id;
    private String username;
    @Getter(AccessLevel.NONE)
    private String password;
    @OneToOne(
            cascade = PERSIST
    )
    private Portfolio portfolio;
    @Enumerated(EnumType.STRING)
    private CurrencyType preferredCurrency;
    @Setter
    private BigDecimal currencyBalance;
    @OneToMany(
            cascade = PERSIST
    )
    @JoinColumn(
            name = "transactionId",
            referencedColumnName = "id"
    )
    private List<Transaction> transactions;

    public User() {
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
