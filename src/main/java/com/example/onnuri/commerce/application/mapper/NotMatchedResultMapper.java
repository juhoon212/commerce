package com.example.onnuri.commerce.application.mapper;

import com.example.onnuri.commerce.domain.account.BankTransactionRecord;
import com.example.onnuri.commerce.domain.policy.IntegratedPolicy;
import com.example.onnuri.commerce.infrastructure.persistence.entity.AccountEntity;
import com.example.onnuri.commerce.infrastructure.persistence.entity.CategoryEntity;
import com.example.onnuri.commerce.infrastructure.persistence.entity.CompanyEntity;
import com.example.onnuri.commerce.infrastructure.persistence.entity.KeywordEntity;
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
