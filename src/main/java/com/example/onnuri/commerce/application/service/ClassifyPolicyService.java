package com.example.onnuri.commerce.application.service;

import com.example.onnuri.commerce.application.helper.PolicyClassifier;
import com.example.onnuri.commerce.domain.policy.*;
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
