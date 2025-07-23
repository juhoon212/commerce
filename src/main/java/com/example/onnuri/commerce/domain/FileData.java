package com.example.onnuri.commerce.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FileData {

    private List<String> headers;
    private List<List<String>> data;

    public static FileData generateFileData(final List<String> headers, final List<List<String>> data) {
        log.info("Generating file data headers = {}, data = {}", headers, data);
        return new FileData(headers, data);
    }
}
