package org.amigoscode.customer.alta.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @className: CardDto
 * @date: 10.01.2024
 * @author: Uralbaev Diyorbek
 */

@Getter
@Setter
public class CardDto {

    private Integer id;
    private String type;
    private Double balance;
    private Integer userId;
    private String number;
    private String expire;
    private int status;
    private LocalDateTime createdAt;
    private List<CardTransactionDto> cardTransactions;
}
