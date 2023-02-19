package com.hexagonal.coupon.common.exception;

import static com.hexagonal.coupon.common.exception.ErrorCode.DUPLICATE_COUPON;

public class DuplicateCouponException extends CustomException {
    public DuplicateCouponException() {
        super(DUPLICATE_COUPON);
    }

    public DuplicateCouponException(String message) {
        super(message, DUPLICATE_COUPON);
    }
}
