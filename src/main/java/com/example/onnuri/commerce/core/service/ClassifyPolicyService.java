package com.example.onnuri.commerce.core.service;

import com.example.onnuri.commerce.core.domain.policy.IntegratedPolicy;
import com.example.onnuri.commerce.core.domain.policy.Policy;
import com.example.onnuri.commerce.core.helper.PolicyClassifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClassifyPolicyService {

    private final PolicyClassifier policyClassifier;

    public List<IntegratedPolicy> classifyPolicy(final Policy policy) {
        if (policy == null) {
            throw new IllegalArgumentException("정책파일을 다시 확인해주시기 바랍니다.");
        }
        return policyClassifier.classifyPolicy(policy.getCompanyListPolicy());
    }
}
