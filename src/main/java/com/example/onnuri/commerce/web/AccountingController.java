package com.example.onnuri.commerce.web;

import com.example.onnuri.commerce.application.service.*;
import com.example.onnuri.commerce.domain.account.Account;
import com.example.onnuri.commerce.domain.BaseFile;
import com.example.onnuri.commerce.domain.policy.IntegratedPolicy;
import com.example.onnuri.commerce.domain.policy.Policy;
import com.example.onnuri.commerce.exception.NotFoundFileException;
import com.example.onnuri.commerce.web.vo.GetClassifiedResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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
    private final GetClassifiedResultService getClassifiedResultService;

    private final FileValidator fileValidator;

    @PostMapping("/process")
    public ResponseEntity<?> processAutoAccounting(@RequestPart final List<MultipartFile> files) {
        if (files.isEmpty()) {
            log.error("File is empty");
            throw new NotFoundFileException("파일이 비어있습니다.");
        }

        if (files.size() != 2) {
            log.error("파일은 csv && json 파일 두개이상을 보내주세요");
            throw new IllegalArgumentException("파일 갯수는 2개 보내주세요 (csv, json 파일)");
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
        log.info("Processing auto accounting accounts: {}, integratedPolicies = {}", accounts, integratedPolicies);

        // 자바 메모리에 임시 저장
        cacheAccountingPolicySetService.cacheAccountingPolicySet(key, accounts, integratedPolicies);

        // 저장된 파일데이터 매칭 및 저장
        autoFileDataGenerateService.generateFileData(key);

        return ResponseEntity.ok("성공적으로 저장되었습니다.");
    }

    @GetMapping("/records")
    public ResponseEntity<List<GetClassifiedResultResponse>> getClassifiedResult(
            @RequestParam(required = false) final String companyId
    ) {
        // companyId가 없으면 전체조회
        return ResponseEntity.ok(getClassifiedResultService.getClassifiedResults(companyId));
    }
}
