package com.InventoryManagement.InventoryManagement.exception;

import org.springframework.http.HttpStatus;

public class UserCustomException extends RuntimeException
{
    //runtime exception ke ander jo bhi exception honge woo already usercustomeexception se catch ho jayega
    private final HttpStatus httpStatus;
    public UserCustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public UserCustomException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
