package com.hexagonal.coupon.common.exception;

import static com.hexagonal.coupon.common.exception.ErrorCode.*;

public class MemberCouponNotExistException extends CustomException {
    public MemberCouponNotExistException() {
        super(MEMBER_COUPON_NOT_EXIST);
    }

    public MemberCouponNotExistException(String message) {
        super(message, MEMBER_COUPON_NOT_EXIST);
    }
}
