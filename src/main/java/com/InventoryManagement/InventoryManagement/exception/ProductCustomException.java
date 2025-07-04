package com.InventoryManagement.InventoryManagement.exception;

import org.springframework.http.HttpStatus;

public class ProductCustomException extends RuntimeException {
    private final HttpStatus httpStatus;
    public ProductCustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
