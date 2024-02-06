package com.alta.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * @className: CardTransaction
 * @date: 09.01.2024
 * @author: Uralbaev Diyorbek
 */

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "card_transactions")
public class CardTransaction {

    @Id
    @SequenceGenerator(
            name = "card_transaction_id_sequence",
            sequenceName = "card_transaction_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "card_transaction_id_sequence"
    )
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "card_id", insertable = false, updatable = false)
    @JsonBackReference
    private Card card;

    @Column(name = "card_id")
    private String cardId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "type")
    private String type;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "old_balance")
    private Double old_balance;

    @Column(name = "new_balance")
    private Double new_balance;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;
}
