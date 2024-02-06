package org.amigoscode.customer.alta.payload;

import lombok.Getter;
import lombok.Setter;

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
public class UserDto {

    private Integer id;

    private String fio;

    private String email;

    private Integer phoneNumber;

    private int status;

    private LocalDate birthDate;

    private LocalDateTime createdAt;

    private List<CardDto> cards;

}
