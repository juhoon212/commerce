package com.example.onnuri.commerce.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleReadExcelException(ReadCsvFileException e) {
        log.error("ReadExcelException occurred: {}", e.getMessage(), e);
        return ResponseEntity.internalServerError()
                .body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleNotFoundFileException(NotFoundFileException e) {
        log.error("NotFoundFileException occurred: {}", e.getMessage(), e);
        return ResponseEntity.internalServerError()
                .body(e.getMessage());
    }
}
