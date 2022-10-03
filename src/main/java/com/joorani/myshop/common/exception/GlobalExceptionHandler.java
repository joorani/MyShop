package com.joorani.myshop.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DBEmptyDataException.class)
    public ResponseEntity<ErrorResponse> handleDataAccessException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.DB_EMPTY_DATA_ERROR, e.getMessage());
        log.error("error: {}, stacktrace: {}", errorResponse, e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<Object> handleCustomException(RestApiException e) {
        ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode(), e.getMessage());
        log.error("error: {}, stacktrace: {}", errorResponse, e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
        log.error("error: {}, stacktrace: {}", errorResponse, e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_VALUE, e.getMessage());
        log.error("error: {}, stacktrace: {}", errorResponse, e);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
