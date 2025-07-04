package com.InventoryManagement.InventoryManagement.controller.advice;

import com.InventoryManagement.InventoryManagement.exception.UserCustomException;
import com.InventoryManagement.InventoryManagement.model.response.ErrorResponse;
import com.InventoryManagement.InventoryManagement.model.response.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(UserCustomException.class)
    public ResponseEntity<ErrorResponse> handleUserException(UserCustomException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getHttpStatus().getReasonPhrase(),
                ex.getMessage(),
                ex.getHttpStatus().toString(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorResponse>> handleValidationException(MethodArgumentNotValidException ex) {
        List<ValidationErrorResponse> errorResponses = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationErrorResponse(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                ))
                .toList();

        return new ResponseEntity<>(errorResponses, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Internal Server Error",
                ex.getMessage(),
                HttpStatus. INTERNAL_SERVER_ERROR.toString(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
