package com.pruebabisa.blog.exception;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pruebabisa.blog.common.dto.ErrorResponse;
import com.pruebabisa.blog.exception.custom.BusinessRuleException;
import com.pruebabisa.blog.exception.custom.DuplicateResourceException;
import com.pruebabisa.blog.exception.custom.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse<String>> handleResourceNotFound(
            ResourceNotFoundException ex, HttpServletRequest request) {
        log.error("Resource not found: {}", ex.getMessage());

        return new ResponseEntity<>(ErrorResponse.<String>builder()
                .timestamp(new Date().toString())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse<String>> handleDuplicateResource(
            DuplicateResourceException ex, HttpServletRequest request) {
        log.error("Duplicate resource: {}", ex.getMessage());

        return new ResponseEntity<>(ErrorResponse.<String>builder()
                .timestamp(new Date().toString())
                .statusCode(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build(), new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse<String>> handleBusinessRule(
            BusinessRuleException ex, HttpServletRequest request) {
        log.error("Business rule violation: {}", ex.getMessage());

        return new ResponseEntity<>(ErrorResponse.<String>builder()
                .timestamp(new Date().toString())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse<List<String>>> handleValidationException(MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        log.error("Error validations : {}", ex.getMessage());
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<>(ErrorResponse.<List<String>>builder()
                .timestamp(new Date().toString())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(errors)
                .path(request.getRequestURI())
                .build(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse<String>> handleException(Exception ex, HttpServletRequest request) {
        log.error("Common error : {}", ex.getMessage());
        return new ResponseEntity<>(ErrorResponse.<String>builder()
                .timestamp(new Date().toString())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
