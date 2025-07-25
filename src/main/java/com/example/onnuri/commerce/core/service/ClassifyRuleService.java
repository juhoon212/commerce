package com.example.onnuri.commerce.core.service;

import com.example.onnuri.commerce.core.domain.policy.Policy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClassifyRuleService {

    public Policy classifyRule(final MultipartFile file) {
        return null;
    }

}
