package com.alta.repository;

import com.alta.entity.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @className: UserRepository
 * @date: 09.01.2024
 * @author: Uralbaev Diyorbek
 */

public interface UserRepository extends R2dbcRepository<User, Integer> {


//    @Query(value = "SELECT\n" +
//            "    u.id AS user_id,\n" +
//            "    u.fio,\n" +
//            "    u.email,\n" +
//            "    c.id AS card_id,\n" +
//            "    c.card_name AS card_name,\n" +
//            "    ct.id AS transaction_id,\n" +
//            "    ct.type AS transaction_type,\n" +
//            "    ct.amount,\n" +
//            "    ct.old_balance,\n" +
//            "    ct.new_balance\n" +
//            "FROM\n" +
//            "    users u\n" +
//            "        JOIN\n" +
//            "    cards c ON u.id = c.user_id\n" +
//            "        LEFT JOIN\n" +
//            "    card_transactions ct ON c.id = ct.card_id\n" +
//            "ORDER BY\n" +
//            "    u.id, c.id, ct.id")
//    List<User> get();
}
