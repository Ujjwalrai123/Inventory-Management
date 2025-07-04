package com.InventoryManagement.InventoryManagement.model.response;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String error;
    private String message;
    private String statusCode;
    private LocalDateTime timestamp;

    public ErrorResponse() {
    }

    public ErrorResponse(String error, String message, String statusCode, LocalDateTime timestamp) {
        this.error = error;
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
