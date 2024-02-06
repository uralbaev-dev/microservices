package com.alta.payload;

import lombok.Getter;
import lombok.Setter;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @className: UserDto
 * @date: 10.01.2024
 * @author: Uralbaev Diyorbek
 */

@Getter
@Setter
public class UserDto extends Flux<UserDto> {

    private Integer id;

    private String fio;

    private String email;

    private Integer phoneNumber;

    private int status;

    private LocalDate birthDate;

    private LocalDateTime createdAt;

    private List<CardDto> cards;

    @Override
    public void subscribe(CoreSubscriber<? super UserDto> coreSubscriber) {

    }
}
