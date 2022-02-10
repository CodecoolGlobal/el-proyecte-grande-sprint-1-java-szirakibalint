package com.codecool.codecoin.repositories;

import com.codecool.codecoin.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Set<Transaction> getAllByUserId(Long userId);
}
