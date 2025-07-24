package com.example.onnuri.commerce.application.helper;

import com.example.onnuri.commerce.application.mapper.PolicyJsonMapper;
import com.example.onnuri.commerce.domain.account.Account;
import com.example.onnuri.commerce.application.dto.CompanyListPolicyDto;
import com.example.onnuri.commerce.domain.policy.CompanyListPolicy;
import com.example.onnuri.commerce.domain.policy.Policy;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileReader {

    private final ObjectMapper objectMapper;
    private final PolicyJsonMapper policyJsonMapper;

    public Account readCsvFile(final MultipartFile file) {
        List<String> headers = new ArrayList<>();
        List<List<String>> dataFrames = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            int headerCount = 0;
            while ((line = reader.readLine()) != null) {
                if (headerCount == 0) {
                    // 첫 번째 줄은 헤더로 간주
                    final List<String> headerValues = Arrays.asList(line.split(","));
                    log.info("headerValues = {}", headerValues);

                    headers.addAll(headerValues);
                    headerCount++;
                    continue;
                }
                List<String> row = Arrays.asList(line.split(","));
                dataFrames.add(row);
            }
        } catch (IOException e) {
            throw new RuntimeException("CSV 읽기 실패", e);
        }
        log.info("result = {}", dataFrames);
        log.info("headers = {}", headers);

        return Account.generateAccount(headers, dataFrames);
    }

    public Policy readJsonFile(final MultipartFile file) {

        try {
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SnakeCaseStrategy.INSTANCE);
            final CompanyListPolicyDto companyListPolicyDto = objectMapper.readValue(file.getBytes(), CompanyListPolicyDto.class);
            log.info("companyList = {}", companyListPolicyDto);

            // dto에서 도메인 추출
            final CompanyListPolicy companyListPolicy = policyJsonMapper.mapToCompanyListPolicy(companyListPolicyDto);

            return Policy.generatePolicy(companyListPolicy);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
