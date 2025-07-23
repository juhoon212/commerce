package com.example.onnuri.commerce.exception;

public class ReadCsvFileException extends RuntimeException{

    public ReadCsvFileException() {
        super();
    }

    public ReadCsvFileException(String message) {
        super(message);
    }

    public ReadCsvFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadCsvFileException(Throwable cause) {
        super(cause);
    }
}
