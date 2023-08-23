package org.amigoscode.customer;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @className: CustomerRepository
 * @date: 14.08.2023
 * @author: Uralbaev Diyorbek
 */

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
