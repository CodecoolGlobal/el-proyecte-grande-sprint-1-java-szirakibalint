package com.codecool.codecoin.repositories;

import com.codecool.codecoin.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
