package com.example.onnuri.commerce.exception;

public class NotFoundFileException extends RuntimeException{

    public NotFoundFileException() {
        super();
    }

    public NotFoundFileException(String message) {
        super(message);
    }

    public NotFoundFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundFileException(Throwable cause) {
        super(cause);
    }
}
