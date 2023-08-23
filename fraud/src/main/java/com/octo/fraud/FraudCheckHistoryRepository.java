package com.octo.fraud;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className: FraudCheckHistoryRepository
 * @date: 15.08.2023
 * @author: Uralbaev Diyorbek
 */

public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory, Integer> {
}
