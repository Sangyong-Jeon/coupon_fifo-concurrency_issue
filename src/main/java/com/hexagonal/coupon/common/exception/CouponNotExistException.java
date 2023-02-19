package com.hexagonal.coupon.common.exception;

import static com.hexagonal.coupon.common.exception.ErrorCode.*;

public class CouponNotExistException extends CustomException {
    public CouponNotExistException() {
        super(COUPON_NOT_EXIST);
    }

    public CouponNotExistException(String message) {
        super(message, COUPON_NOT_EXIST);
    }
}
