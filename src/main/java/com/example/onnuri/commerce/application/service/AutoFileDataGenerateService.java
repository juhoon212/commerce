package com.example.onnuri.commerce.application.service;

import com.example.onnuri.commerce.application.dto.ClassifyDataFileResultWrapper;
import com.example.onnuri.commerce.domain.MatchedAccountingPolicySet;
import com.example.onnuri.commerce.domain.account.Account;
import com.example.onnuri.commerce.domain.AccountingPolicySet;
import com.example.onnuri.commerce.domain.account.BankTransactionRecord;
import com.example.onnuri.commerce.domain.policy.IntegratedPolicy;
import com.example.onnuri.commerce.domain.policy.Policy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AutoFileDataGenerateService {

    private final FileDataStoreGenerator fileDataStoreGenerator;
    private final SaveClassifiedDataService saveClassifiedDataService;

    public void generateFileData(final String key) {
        // 이 제네레이터에서 매칭하고
        final ClassifyDataFileResultWrapper results = fileDataStoreGenerator.generateFileDataStore(key);

        final List<MatchedAccountingPolicySet> matchedResults = results.matchedAccountingPolicySets();
        final List<BankTransactionRecord> notMatchedResults = results.unmatchedBankTransactionRecords();

        // 해당 list를 돌면서 매칭 된 내용 DB에 저장
        saveClassifiedDataService.saveClassifiedDataResults(matchedResults, notMatchedResults);

    }
}
