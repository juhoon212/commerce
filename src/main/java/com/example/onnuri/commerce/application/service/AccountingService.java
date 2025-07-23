package com.example.onnuri.commerce.application.service;

import com.example.onnuri.commerce.helper.CsvFileReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountingService {

    private final CsvFileReader csvFileReader;
    private final ClassifyFileDataService classifyFileDataService;

    public void readExcelFile(final MultipartFile file) {
        final List<List<String>> csvFileData = csvFileReader.readCsvFile(file);

        classifyFileDataService.classifyFileData(csvFileData);

    }
}
