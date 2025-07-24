package com.example.onnuri.commerce.application.service;

import com.example.onnuri.commerce.domain.account.Account;
import com.example.onnuri.commerce.domain.AccountingPolicySet;
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

    public void generateFileData(final String key) {
        // 장부 적요 필드에 있는 키워드들이 정책에 있는 keyword에 포함되어 있으면

        fileDataStoreGenerator.generateFileDataStore(key);

        // 해당 키워드가 등록되어있는 회사로 분류

    }
}
