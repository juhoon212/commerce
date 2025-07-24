package com.example.onnuri.commerce.domain.account;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class BankTransactionRecord {
    private LocalDateTime transactionDateTime; // 거래 일시
    private String description; // 적요
    private BigDecimal depositAmount; // 입금액
    private BigDecimal withdrawAmount; // 출금액
    private BigDecimal balanceAfterTransaction; // 거래 후 잔액
    private String transactionBranch; // 거래 지점

    public static BankTransactionRecord generateBankTransactionRecord(
            final LocalDateTime transactionDateTime,
            final String description,
            final BigDecimal depositAmount,
            final BigDecimal withdrawAmount,
            final BigDecimal balanceAfterTransaction,
            final String transactionBranch
    ) {
        return new BankTransactionRecord(
                transactionDateTime,
                description,
                depositAmount,
                withdrawAmount,
                balanceAfterTransaction,
                transactionBranch
        );
    }



}
