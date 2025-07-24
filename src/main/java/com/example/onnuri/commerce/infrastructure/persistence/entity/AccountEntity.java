package com.example.onnuri.commerce.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Table(name = "account")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id", length = 128)
    private String companyId;

    @Column(name = "category_id", length = 128)
    private String categoryId;

    @Column
    private String keyword;

    @Column(name = "transaction_datetime", nullable = false)
    private LocalDateTime transactionDatetime;

    @Column(name = "description", nullable = false, length = 128)
    private String description;

    @Column(name = "deposit_amount", nullable = false, precision = 15, scale = 4)
    private BigDecimal depositAmount;

    @Column(name = "withdraw_amount", nullable = false, precision = 15, scale = 4)
    private BigDecimal withdrawAmount;

    @Column(name = "balance_after_transaction", nullable = false, precision = 15, scale = 4)
    private BigDecimal balanceAfterTransaction;

    @Column(name = "transaction_branch", nullable = false, length = 64)
    private String transactionBranch;

    @Column(name = "is_matched", nullable = false)
    private Boolean isMatched;
}
