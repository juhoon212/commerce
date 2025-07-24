package com.example.onnuri.commerce.adapter.in;

import com.example.onnuri.commerce.application.service.AutoFileDataGenerateService;
import com.example.onnuri.commerce.application.service.CacheAccountingPolicySetService;
import com.example.onnuri.commerce.application.service.ClassifyFileDataService;
import com.example.onnuri.commerce.application.service.ClassifyPolicyService;
import com.example.onnuri.commerce.domain.account.Account;
import com.example.onnuri.commerce.domain.BaseFile;
import com.example.onnuri.commerce.domain.policy.IntegratedPolicy;
import com.example.onnuri.commerce.domain.policy.Policy;
import com.example.onnuri.commerce.exception.NotFoundFileException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounting")
@RequiredArgsConstructor
public class AccountingController {

    private final ClassifyFileDataService classifyFileDataService;
    private final AutoFileDataGenerateService autoFileDataGenerateService;
    private final ClassifyPolicyService classifyPolicyService;
    private final CacheAccountingPolicySetService cacheAccountingPolicySetService;

    private final FileValidator fileValidator;

    @PostMapping("/process")
    public void processAutoAccounting(@RequestPart final List<MultipartFile> files) {
        if (files.isEmpty()) {
            log.error("File is empty");
            throw new NotFoundFileException("File is empty");
        }

        List<Account> accounts = new ArrayList<>();
        List<IntegratedPolicy> integratedPolicies = null;
        String key = null;

        for (MultipartFile file : files) {
            // file validation
            fileValidator.validateFile(file);
            final BaseFile baseFile = classifyFileDataService.classifyFileData(file);

            if (baseFile instanceof Account account) {
                accounts.add(account);
                key = file.getOriginalFilename();
            } else if (baseFile instanceof Policy policy) {
                // 정책 분류 서비스 호출
                // List로 계속 묶여 있어서 푸는 역할
                integratedPolicies = classifyPolicyService.classifyPolicy(policy);
            }
        }
        cacheAccountingPolicySetService.cacheAccountingPolicySet(key, accounts, integratedPolicies);

        log.info("Processing auto accounting accounts: {}, integratedPolicies = {}", accounts, integratedPolicies);

        autoFileDataGenerateService.generateFileData(key);
    }
}
