package com.alta.payload;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: CardTransactionDTO
 * @date: 10.01.2024
 * @author: Uralbaev Diyorbek
 */

@Getter
@Setter
public class CardTransactionDto {

    private Integer id;
    private String type;
    private Double amount;
    private Double oldBalance;
    private Double newBalance;
    private Integer userId;
    private Integer cardId;

}
