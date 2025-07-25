package com.example.onnuri.commerce.core.mapper;

import com.example.onnuri.commerce.core.domain.account.BankTransactionRecord;
import com.example.onnuri.commerce.infrastructure.persistence.entity.AccountEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotMatchedResultMapper {

    public AccountEntity mapToAccountEntity(final BankTransactionRecord record) {
        return AccountEntity.builder()
                .transactionDatetime(record.getTransactionDateTime())
                .description(record.getDescription())
                .depositAmount(record.getDepositAmount())
                .withdrawAmount(record.getWithdrawAmount())
                .balanceAfterTransaction(record.getBalanceAfterTransaction())
                .transactionBranch(record.getTransactionBranch())
                .isMatched(Boolean.FALSE)
                .build();
    }
}
