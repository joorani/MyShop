package com.joorani.myshop.common.exception;

import lombok.Getter;

@Getter
public class DBEmptyDataException extends BaseException{

    private String message;

    public DBEmptyDataException(String message) {
        this.message = message;
    }
}
