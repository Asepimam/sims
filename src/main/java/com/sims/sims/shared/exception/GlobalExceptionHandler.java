package com.sims.sims.shared.exception;

import java.util.stream.Collectors;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sims.sims.shared.dtos.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle validation errors dari @Valid
     * Status: 102 - Validation Failed
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        
        String errorMessages = ex.getBindingResult()
            .getAllErrors()
            .stream()
            .map(error -> error.getDefaultMessage())
            .collect(Collectors.joining(", "));
        
        ResponseDto response = new ResponseDto(
            102,
            errorMessages,
            null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handle authentication errors
     * Status: 103 - authentication failed
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ResponseDto> handleAuthenticationError(AuthenticationException ex) {
        ResponseDto response = new ResponseDto(
            103, 
            ex.getMessage(),
            null
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    /**
     * Handle business logic exceptions
     * Status: 400 - Bad Request
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDto> handleBusinessException(BusinessException ex) {
        ResponseDto response = new ResponseDto(
            102,
            ex.getMessage(),
            null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handle resource not found errors
     * Status: 404 - Not Found
     * Handle resource not found errors
     * Status: 404 - Not Found
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseDto> handleResourceNotFound(ResourceNotFoundException ex) {
        ResponseDto response = new ResponseDto(
            102,
            ex.getMessage(),
            null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    /**
     * Handle EmptyResultDataAccessException
     * Status: 404 - Not Found
     */
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ResponseDto> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        ResponseDto response = new ResponseDto(
            102,
            "Resource not found",
            null
        );
        
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    /**
     * Handle database errors
     * Status: 500 - Database Error
     */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseDto> handleDataAccessException(DataAccessException ex) {
        ResponseDto response = new ResponseDto(
            500,
            "Database error: " + ex.getMessage(),
            null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * Handle general exceptions
     * Status: 500 - Internal Server Error
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseDto> handleGeneralException(Exception ex) {
        ResponseDto response = new ResponseDto(
            500,
            "Internal server error: " + ex.getMessage(),
            null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
