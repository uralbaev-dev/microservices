package org.amigoscode.customer.alta.repository;


import org.amigoscode.customer.alta.entity.CardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className: UserRepository
 * @date: 09.01.2024
 * @author: Uralbaev Diyorbek
 */

public interface CardTransactionRepository extends JpaRepository<CardTransaction, Integer> {
}
