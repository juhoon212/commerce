package com.example.onnuri.commerce.adapter;

import com.example.onnuri.commerce.exception.NotFoundFileException;
import com.example.onnuri.commerce.application.service.AccountingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounting")
@RequiredArgsConstructor
public class AccountingController {

    private final AccountingService accountingService;

    @PostMapping("/process")
    public void processAutoAccounting(@RequestPart MultipartFile file) {
        if (file.isEmpty()) {
            log.error("File is empty");
            throw new NotFoundFileException("File is empty");
        }
        accountingService.saveAccountingDataFile(file);
    }
}
