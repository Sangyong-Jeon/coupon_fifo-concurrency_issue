package com.hexagonal.coupon.common.exception;

import static com.hexagonal.coupon.common.exception.ErrorCode.MEMBER_NOT_EXIST;

public class MemberNotExistException extends CustomException {

    public MemberNotExistException() {
        super(MEMBER_NOT_EXIST);
    }

    public MemberNotExistException(String message) {
        super(message, MEMBER_NOT_EXIST);
    }
}
