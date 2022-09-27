package com.joorani.myshop.common.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/*
사용자가 특정 도메인에서 정의해서 사용할 에러코드
 */
@Getter
@RequiredArgsConstructor
public enum CustomErrorCode implements ErrorCode {

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "데이터가 이미 존재합니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
