package com.example.onnuri.commerce.application.service;

import com.example.onnuri.commerce.domain.Account;
import com.example.onnuri.commerce.domain.AccountingPolicySet;
import com.example.onnuri.commerce.domain.Policy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AutoFileDataGenerateService {

    private final FileDataStore fileDataStore;

    public void generateFileData(final List<Account> accounts, final Policy policy, final String key) {

        // 회계 장부 데이터와 policy를 이용하여 AccountingPolicySet 생성
        final AccountingPolicySet accountingPolicySet = AccountingPolicySet.generateAccountingPolicySet(accounts, policy);

        if (fileDataStore.isRegisteredKey(key)) {
            throw new RuntimeException("처리중인 파일입니다. 파일 이름을 확인해주세요");
        }

        // 생성된 AccountingPolicySet을 FileDataStore에 저장
        fileDataStore.addFileData(key, accountingPolicySet);



    }
}
