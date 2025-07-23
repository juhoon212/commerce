package com.example.onnuri.commerce.helper;

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
public class CsvFileReader {

    public List<List<String>> readCsvFile(final MultipartFile file) {
        List<List<String>> result = new ArrayList<>();
        List<List<String>> headers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> row = Arrays.asList(line.split(","));
                result.add(row);
            }
        } catch (IOException e) {
            throw new RuntimeException("CSV 읽기 실패", e);
        }
        log.info("result = {}", result);
        log.info("headers = {}", headers);
        return result;
    }
}
