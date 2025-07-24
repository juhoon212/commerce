package com.example.onnuri.commerce.application.service;

import com.example.onnuri.commerce.application.mapper.MatchedResultMapper;
import com.example.onnuri.commerce.application.mapper.NotMatchedResultMapper;
import com.example.onnuri.commerce.domain.MatchedAccountingPolicySet;
import com.example.onnuri.commerce.domain.account.BankTransactionRecord;
import com.example.onnuri.commerce.infrastructure.persistence.entity.AccountEntity;
import com.example.onnuri.commerce.infrastructure.persistence.entity.CategoryEntity;
import com.example.onnuri.commerce.infrastructure.persistence.entity.CompanyEntity;
import com.example.onnuri.commerce.infrastructure.persistence.entity.KeywordEntity;
import com.example.onnuri.commerce.infrastructure.persistence.repository.AccountRepository;
import com.example.onnuri.commerce.infrastructure.persistence.repository.CategoryRepository;
import com.example.onnuri.commerce.infrastructure.persistence.repository.CompanyRepository;
import com.example.onnuri.commerce.infrastructure.persistence.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaveClassifiedDataService {
    private final AccountRepository accountRepository;
    private final CompanyRepository companyRepository;
    private final CategoryRepository categoryRepository;
    private final KeywordRepository keywordRepository;

    private final MatchedResultMapper matchedResultMapper;
    private final NotMatchedResultMapper notMatchedResultMapper;

    @Transactional
    public void saveClassifiedDataResults(
            final List<MatchedAccountingPolicySet> matchedResults,
            final List<BankTransactionRecord> notMatchedResults
    ) {
        log.info("Saving matchedResults: {}, notMatchedResults: {}",
                 matchedResults, notMatchedResults);

        // 매칭된 결과 저장
        saveMatchedResults(matchedResults);
        // 매칭안된 결과 저장
        saveNotMatchedResults(notMatchedResults);
    }

    private void saveNotMatchedResults(final List<BankTransactionRecord> notMatchedResults) {
        List<AccountEntity> notMatchedAccountEntities = new ArrayList<>();
        for (BankTransactionRecord notMatchedResult : notMatchedResults) {
            final AccountEntity accountEntity =
                    notMatchedResultMapper.mapToAccountEntity(notMatchedResult);
            notMatchedAccountEntities.add(accountEntity);
        }
        accountRepository.saveAll(notMatchedAccountEntities);
    }

    private void saveMatchedResults(final List<MatchedAccountingPolicySet> matchedResults) {
        List<CompanyEntity> companyEntities = new ArrayList<>();
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        List<KeywordEntity> keywordEntities = new ArrayList<>();
        List<AccountEntity> accountEntities = new ArrayList<>();

        // 매칭된 결과를 저장
        for (MatchedAccountingPolicySet matchedResult : matchedResults) {
            final CompanyEntity companyEntity =
                    matchedResultMapper.mapToCompanyEntity(matchedResult.getIntegratedPolicy());

            final CategoryEntity categoryEntity =
                    matchedResultMapper.mapToCategoryEntity(matchedResult.getIntegratedPolicy());

            final KeywordEntity keywordEntity =
                    matchedResultMapper.mapToKeywordEntity(matchedResult.getIntegratedPolicy());

            final AccountEntity accountEntity =
                    matchedResultMapper.mapToAccountEntity(
                            matchedResult.getIntegratedPolicy(), matchedResult.getBankTransactionRecord());

            // list에 저장
            companyEntities.add(companyEntity);
            categoryEntities.add(categoryEntity);
            keywordEntities.add(keywordEntity);
            accountEntities.add(accountEntity);
        }

        // bulk 연산으로 모두 한꺼번에 저장
        companyRepository.saveAll(companyEntities);
        categoryRepository.saveAll(categoryEntities);
        keywordRepository.saveAll(keywordEntities);
        accountRepository.saveAll(accountEntities);
    }
}
