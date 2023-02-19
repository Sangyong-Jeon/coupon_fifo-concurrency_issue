package com.hexagonal.coupon.common.exception;

import static com.hexagonal.coupon.common.exception.ErrorCode.COUPON_NOT_REMAIN;

public class CouponNotRemainException extends CustomException {
    public CouponNotRemainException() {
        super(COUPON_NOT_REMAIN);
    }

    public CouponNotRemainException(String message) {
        super(message, COUPON_NOT_REMAIN);
    }
}
