package com.joorani.myshop.common.exception;


import lombok.Getter;

@Getter
public class ErrorResponse {

    private String errorMessage;
    private String errorCode;

    public static ErrorResponse of(ErrorCode errorCode, String message) {
        return new ErrorResponse(errorCode.getCode(), message);
    }

    public ErrorResponse(String errorCode, String message) {
        this.errorMessage = message;
        this.errorCode = errorCode;
    }
}