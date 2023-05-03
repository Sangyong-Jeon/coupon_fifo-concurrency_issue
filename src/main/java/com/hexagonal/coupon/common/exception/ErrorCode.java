package com.hexagonal.coupon.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public enum ErrorCode {
    COUPON_NOT_REMAIN("400", "쿠폰이 모두 소진되었습니다.", BAD_REQUEST),
    COUPON_NOT_EXIST("400", "쿠폰이 존재하지 않습니다.", BAD_REQUEST),
    DUPLICATE_COUPON("400", "쿠폰을 이미 보유하고 있습니다.", BAD_REQUEST),
    USED_COUPON("400", "사용된 쿠폰입니다.", BAD_REQUEST),
    MEMBER_COUPON_NOT_EXIST("400", "사용되지 않은 회원쿠폰이 존재하지 않습니다.", BAD_REQUEST),
    MEMBER_NOT_EXIST("400", "회원이 존재하지 않습니다.", BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
