package com.codecool.codecoin.repositories;

import com.codecool.codecoin.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
}
