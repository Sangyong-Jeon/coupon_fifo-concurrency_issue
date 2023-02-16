package com.hexagonal.coupon.application.port.out;

public interface CreateMemberCouponPort {

    void createMemberCoupon(Long memberId, Long couponId);
}
