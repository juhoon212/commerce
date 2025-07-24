package com.example.onnuri.commerce.application.service;

import com.example.onnuri.commerce.application.dto.ClassifyDataFileResultWrapper;
import com.example.onnuri.commerce.domain.AccountingPolicySet;
import com.example.onnuri.commerce.domain.MatchedAccountingPolicySet;
import com.example.onnuri.commerce.domain.account.Account;
import com.example.onnuri.commerce.domain.account.BankTransactionRecord;
import com.example.onnuri.commerce.domain.policy.IntegratedPolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileDataStoreGenerator {

    private final FileDataStore fileDataStore;
    
    public ClassifyDataFileResultWrapper generateFileDataStore(final String key) {
        final ConcurrentLinkedQueue<AccountingPolicySet> queue = fileDataStore.getQueue(key);

        log.info("queue size: {}", queue.size());

        // 매칭 객체 담을 list 생성
        List<MatchedAccountingPolicySet> matchedAccountingPolicySets = new ArrayList<>();
        List<BankTransactionRecord> notMatchedRecords = new ArrayList<>();

        try {
            while (!queue.isEmpty()) {
                final AccountingPolicySet accountingPolicySet = queue.poll();
                final List<Account> accounts = accountingPolicySet.getAccounts();
                final List<IntegratedPolicy> integratedPolicies = accountingPolicySet.getIntegratedPolicies();

                for (Account account : accounts) {
                    final List<BankTransactionRecord> bankTransactionRecords = account.getBankTransactionRecords();

                    // 장부의 각 row
                    for (BankTransactionRecord bankTransactionRecord : bankTransactionRecords) {
                        String currentDescription = bankTransactionRecord.getDescription();
                        boolean matched = false;

                        for (IntegratedPolicy policy : integratedPolicies) {
                            log.info("currentPolicy: {}", policy.getKeyword());

                            // 적요 가 정책의 키워드에 포함되어 있는지 확인
                            if (currentDescription.contains(policy.getKeyword())) {
                                // 매칭되는 것들 담는다.
                                final MatchedAccountingPolicySet matchedAccountingPolicySet =
                                        MatchedAccountingPolicySet.generateMatchedAccountingPolicySet(bankTransactionRecord, policy);

                                // 하나의 정책에만 매칭된다고 가정 -> 매칭되면 나머지 정책들은 pass
                                log.info("장부 적요 '{}'가 정책 '{}'에 포함되어 있습니다.", currentDescription, policy.getCategoryName());
                                matchedAccountingPolicySets.add(matchedAccountingPolicySet);
                                matched = true;
                                break;
                            }
                        }

                        // 미분류로 분류할 항목들
                        if (!matched) {
                            log.warn("장부 적요 '{}'가 어떤 정책에도 매칭되지 않았습니다.", currentDescription);
                            notMatchedRecords.add(bankTransactionRecord);
                        }
                    }
                }
            }
        } finally {
            // 메모리에서 삭제
            fileDataStore.clearMap(key);
        }

        return new ClassifyDataFileResultWrapper(matchedAccountingPolicySets, notMatchedRecords);
    }
}
