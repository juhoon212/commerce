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

    @ExceptionHandler
    public ResponseEntity<?> handleIllegalFileTypeException(IllegalFileTypeException e) {
        log.error("IllegalFileTypeException occurred: {}", e.getMessage(), e);
        return ResponseEntity.internalServerError()
                .body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("handleIllegalArgumentException occurred: {}", e.getMessage(), e);
        return ResponseEntity.internalServerError()
                .body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        log.error("RuntimeException occurred: {}", e.getMessage(), e);
        return ResponseEntity.internalServerError()
                .body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception e) {
        log.error("Exception occurred: {}", e.getMessage(), e);
        return ResponseEntity.internalServerError()
                .body(e.getMessage());
    }


}
