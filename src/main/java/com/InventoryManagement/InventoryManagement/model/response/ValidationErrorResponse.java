package com.InventoryManagement.InventoryManagement.model.response;

// import java.time.LocalDate;
// import java.time.LocalDateTime;

public class ValidationErrorResponse {
    private String field;
    private String message;

    public ValidationErrorResponse() {
    }

    public ValidationErrorResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
