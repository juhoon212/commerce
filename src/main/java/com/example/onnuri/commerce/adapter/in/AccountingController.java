package com.example.onnuri.commerce.adapter.in;

import com.example.onnuri.commerce.application.service.AutoFileDataGenerateService;
import com.example.onnuri.commerce.application.service.ClassifyFileDataService;
import com.example.onnuri.commerce.domain.Account;
import com.example.onnuri.commerce.domain.BaseFile;
import com.example.onnuri.commerce.domain.Policy;
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

    private final FileValidator fileValidator;

    @PostMapping("/process")
    public void processAutoAccounting(@RequestPart final List<MultipartFile> files) {
        if (files.isEmpty()) {
            log.error("File is empty");
            throw new NotFoundFileException("File is empty");
        }

        List<Account> accounts = new ArrayList<>();
        Policy classifiedPolicy = null;
        String key = null;

        for (MultipartFile file : files) {
            // file validation
            fileValidator.validateFile(file);
            final BaseFile baseFile = classifyFileDataService.classifyFileData(file);

            if (baseFile instanceof Account account) {
                accounts.add(account);
                key = file.getOriginalFilename();
            } else if (baseFile instanceof Policy policy) {
                classifiedPolicy = policy;
            }
        }

        log.info("Processing auto accounting accounts: {}, rule = {}", accounts, classifiedPolicy);

        autoFileDataGenerateService.generateFileData(accounts, classifiedPolicy, key);
    }
}
