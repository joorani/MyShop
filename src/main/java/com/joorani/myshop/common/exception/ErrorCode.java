package com.joorani.myshop.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    /* 400 Client Error */
    INVALID_REQUEST("400100000", "Invalid request", "잘못된 요청입니다."),
    INVALID_VALUE("400100001", "Invalid value in the request", "요청에 잘못된 값이 있습니다."),

    /* 500 Server Error */
    INTERNAL_SERVER_ERROR("500100000", "Internal server error", "서버 오류"),
    DB_EMPTY_DATA_ERROR("D50001", "Empty data from Database", "DB에 데이터가 존재하지 않습니다.")
    ;

    private final String code;
    private final String message;
    private final String detail;

    ErrorCode(String code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }
}