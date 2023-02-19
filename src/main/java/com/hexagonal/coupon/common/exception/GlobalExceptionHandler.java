package com.hexagonal.coupon.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CouponNotRemainException.class)
    ResponseEntity<String> couponNotRemainException(CouponNotRemainException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(DuplicateCouponException.class)
    ResponseEntity<String> duplicateCouponException(DuplicateCouponException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(UsedCouponException.class)
    ResponseEntity<String> usedCouponException(UsedCouponException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
