package com.example.onnuri.commerce.core.service;

import com.example.onnuri.commerce.core.domain.account.Account;
import com.example.onnuri.commerce.core.domain.BaseFile;
import com.example.onnuri.commerce.core.domain.policy.Policy;
import com.example.onnuri.commerce.exception.IllegalFileTypeException;
import com.example.onnuri.commerce.core.helper.FileReader;
import com.example.onnuri.commerce.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClassifyFileDataService {
    private final FileReader fileReader;

    public BaseFile classifyFileData(final MultipartFile file) {
        // 이미 validate된 파일
        final String originalFilename = file.getOriginalFilename();

        if (originalFilename.endsWith(StringUtil.CSV_FILE_PREFIX)) {
            final Account account = fileReader.readCsvFile(file);
            log.info("account = {}", account);
            return account;

        } else if (originalFilename.endsWith(StringUtil.JSON_FILE_PREFIX)) {
            final Policy policy = fileReader.readJsonFile(file);
            log.info("policy = {}", policy);
            return policy;
        }
        throw new IllegalFileTypeException("지원하지 않는 파일 형식입니다. 파일 이름: " + originalFilename);
    }
}
