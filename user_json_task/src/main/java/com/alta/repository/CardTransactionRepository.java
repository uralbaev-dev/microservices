package com.alta.repository;

import com.alta.entity.CardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @className: UserRepository
 * @date: 09.01.2024
 * @author: Uralbaev Diyorbek
 */

public interface CardTransactionRepository extends R2dbcRepository<CardTransaction, Integer> {
    Flux<CardTransaction> findByUserId(Integer id);
}
