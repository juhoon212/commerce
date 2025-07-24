package com.example.onnuri.commerce.exception;

public class IllegalFileTypeException extends RuntimeException {

    public IllegalFileTypeException() {
        super();
    }

    public IllegalFileTypeException(String message) {
        super(message);
    }

    public IllegalFileTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalFileTypeException(Throwable cause) {
        super(cause);
    }
}
