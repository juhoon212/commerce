package com.example.onnuri.commerce.application.service;

import com.example.onnuri.commerce.domain.AccountingPolicySet;
import com.example.onnuri.commerce.domain.account.Account;
import com.example.onnuri.commerce.domain.account.BankTransactionRecord;
import com.example.onnuri.commerce.domain.policy.IntegratedPolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileDataStoreGenerator {

    private final FileDataStore fileDataStore;
    
    public void generateFileDataStore(final String key) {
        final AccountingPolicySet task = fileDataStore.getAccountingPolicySetValue(key);

        if (task == null) {
            log.error("실행할 작업이 없습니다. 키를 확인해주세요: {}", key);
            throw new IllegalArgumentException("데이터 분류에 실패했습니다.");
        }

        final ConcurrentLinkedQueue<AccountingPolicySet> queue = fileDataStore.getQueue(key);

        while (!queue.isEmpty()) {
            final AccountingPolicySet accountingPolicySet = queue.poll();
            final List<Account> accounts = accountingPolicySet.getAccounts();
            final List<IntegratedPolicy> integratedPolicies = accountingPolicySet.getIntegratedPolicies();

            for (Account account : accounts) {
                final List<BankTransactionRecord> bankTransactionRecords = account.getBankTransactionRecords();

                // 장부의 각 row
                for (BankTransactionRecord bankTransactionRecord : bankTransactionRecords) {
                    String currentDescription = bankTransactionRecord.getDescription();

                    for (IntegratedPolicy policy : integratedPolicies) {
                        // 적요 가 정책의 키워드에 포함되어 있는지 확인
                        if (currentDescription.contains(policy.getKeyword())) {
                            // DB에 저장할 목록 cache 해야함
                            log.info("장부 적요 '{}'가 정책 '{}'에 포함되어 있습니다.", currentDescription, policy.getCategoryName());
                        }
                    }
                }
            }
        }
    }
}
