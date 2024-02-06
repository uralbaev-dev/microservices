package org.amigoscode.customer.alta.controller;

import lombok.RequiredArgsConstructor;
import org.amigoscode.customer.alta.entity.User;
import org.amigoscode.customer.alta.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: OpenController
 * @date: 10.01.2024
 * @author: Uralbaev Diyorbek
 */

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OpenController {

    private final Services services;
    private final UserRepository userRepository;

    @GetMapping("/getInfo")
    public ResponseEntity<?> getInfo() {
        return ResponseEntity.ok(services.getAllUsersWithCardsAndTransactions());
    }

    @GetMapping("/getAll")
    public List<User> getTransactions() {
        return userRepository.findAll();
    }
}
