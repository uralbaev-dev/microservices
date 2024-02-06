package com.alta.repository;

import com.alta.entity.Card;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @className: UserRepository
 * @date: 09.01.2024
 * @author: Uralbaev Diyorbek
 */

public interface CardRepository extends R2dbcRepository<Card, Integer> {

}
