package com.octo.fraud;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

/**
 * @className: FraudCheckHistory
 * @date: 15.08.2023
 * @author: Uralbaev Diyorbek
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fraud_check_history")
public class FraudCheckHistory {


    @Id
    @SequenceGenerator(
            name = "fraud_id_sequence",
            sequenceName = "fraud_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fraud_sequence"
    )
    @Column(name = "id")
    private Integer id;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "is_fraudster")
    private Boolean isFraudster;

//    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    @Column(name = "update_at")
//    private LocalDateTime updatedAt;
//
//    @CreatedBy
//    @Column(name = "created_by", nullable = false)
//    private Integer createdBy;
//
//    @LastModifiedBy
//    @Column(name = "updated_by")
//    private Integer updatedBy;
}
