package com.example.onnuri.commerce.application.service;

import com.example.onnuri.commerce.domain.AccountingPolicySet;
import com.example.onnuri.commerce.domain.account.Account;
import com.example.onnuri.commerce.domain.policy.IntegratedPolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheAccountingPolicySetService {

    private final FileDataStore fileDataStore;

    public void cacheAccountingPolicySet(
            final String key,
            final List<Account> accounts,
            final List<IntegratedPolicy> integratedPolicies
    ) {
        final AccountingPolicySet accountingPolicySet = AccountingPolicySet.generateAccountingPolicySet(accounts, integratedPolicies);
        fileDataStore.addFileData(key, accountingPolicySet);
    }
}
