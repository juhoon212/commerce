package com.example.onnuri.commerce.application.mapper;

import com.example.onnuri.commerce.domain.account.Account;
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
public class MatchedResultMapper {

    public CompanyEntity mapToCompanyEntity(final IntegratedPolicy policy) {
        return new CompanyEntity(policy.getCompanyId(), policy.getCompanyName());
    }

    public CategoryEntity mapToCategoryEntity(final IntegratedPolicy policy) {
        return new CategoryEntity(policy.getCompanyId(), policy.getCategoryId(), policy.getCategoryName());
    }

    public KeywordEntity mapToKeywordEntity(final IntegratedPolicy policy) {
        return KeywordEntity.builder()
                .categoryId(policy.getCategoryId())
                .name(policy.getKeyword())
                .build();
    }

    public AccountEntity mapToAccountEntity(
            final IntegratedPolicy policy,
            final BankTransactionRecord record
    ) {
        return AccountEntity.builder()
                .companyId(policy.getCompanyId())
                .categoryId(policy.getCategoryId())
                .keyword(policy.getKeyword())
                .transactionDatetime(record.getTransactionDateTime())
                .description(record.getDescription())
                .depositAmount(record.getDepositAmount())
                .withdrawAmount(record.getWithdrawAmount())
                .balanceAfterTransaction(record.getBalanceAfterTransaction())
                .transactionBranch(record.getTransactionBranch())
                .isMatched(Boolean.TRUE)
                .build();
    }
}
