package org.amigoscode.customer;

import jakarta.persistence.*;
import lombok.*;

/**
 * @className: Customer
 * @date: 14.08.2023
 * @author: Uralbaev Diyorbek
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    @Column(name = "id")
    private Integer id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;
}
