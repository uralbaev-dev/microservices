package com.alta.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @className: User
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
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName = "user_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence"
    )
    @Column(name = "id")
    private Integer id;

    @Column(name = "fio")
    private String fio;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private Integer phoneNumber;

    @Column(name = "status")
    private int status;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @ToString.Exclude
    private List<Card> cards;
}
