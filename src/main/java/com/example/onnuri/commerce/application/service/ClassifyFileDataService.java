package com.example.onnuri.commerce.application.service;

import com.example.onnuri.commerce.domain.FileData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClassifyFileDataService {

    public FileData classifyFileData(final List<List<String>> fileData) {
        for (List<String> data : fileData) {
            log.info("data = {}", data);
        }

        return FileData.generateFileData(fileData.get(0), fileData.subList(1, fileData.size()));
    }
}
