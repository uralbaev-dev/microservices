package org.amigoscode.customer.alta.repository;


import org.amigoscode.customer.alta.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @className: UserRepository
 * @date: 09.01.2024
 * @author: Uralbaev Diyorbek
 */

public interface UserRepository extends JpaRepository<User, Integer> {

//    @Query(value = "select u from users u where u.")
//    List<User> get();
}
