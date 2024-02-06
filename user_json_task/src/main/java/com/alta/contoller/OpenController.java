package com.alta.contoller;

import com.alta.service.Services;
import com.alta.entity.User;
import com.alta.repository.CardRepository;
import com.alta.repository.CardTransactionRepository;
import com.alta.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @className: OpenController
 * @date: 10.01.2024
 * @author: Uralbaev Diyorbek
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class OpenController {

    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final CardTransactionRepository cardTransactionRepository;
    private final Services services;
    @GetMapping("/getALL")
    public Flux<User> getAll() {
        return userRepository.findAll();
    }

}
