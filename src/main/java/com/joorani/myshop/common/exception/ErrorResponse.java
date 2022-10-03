package com.joorani.myshop.common.exception;


import lombok.Getter;

@Getter
public class ErrorResponse {

    private String errorMessage;
    private String errorCode;

    public static ErrorResponse of(ErrorCode errorCode, String message) {
        return new ErrorResponse(errorCode.getCode(), message);
    }

    public ErrorResponse(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}