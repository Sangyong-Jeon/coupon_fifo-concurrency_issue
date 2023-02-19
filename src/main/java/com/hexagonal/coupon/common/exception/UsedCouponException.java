package com.hexagonal.coupon.common.exception;

import static com.hexagonal.coupon.common.exception.ErrorCode.USED_COUPON;

public class UsedCouponException extends CustomException {
    public UsedCouponException() {
        super(USED_COUPON);
    }

    public UsedCouponException(String message) {
        super(message, USED_COUPON);
    }
}
