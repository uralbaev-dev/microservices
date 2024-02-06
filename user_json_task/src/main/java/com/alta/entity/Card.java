package com.alta.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @className: Card
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
@Table(name = "cards")
public class Card {

    @Id
    @SequenceGenerator(
            name = "card_id_sequence",
            sequenceName = "card_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "card_id_sequence"
    )
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonBackReference
    private User user;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "card_name")
    private String name;

    @Column(name = "card_type")
    private String type;

    @Column(name = "card_number")
    private String number;

    @Column(name = "card_expire")
    private String expire;

    @Column(name = "card_balance")
    private Double balance;

    @Column(name = "card_status")
    private int status;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
//    @ToString.Exclude
    private List<CardTransaction> cardTransactions;

}
