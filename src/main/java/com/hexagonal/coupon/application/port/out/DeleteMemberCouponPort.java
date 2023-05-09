package com.hexagonal.coupon.application.port.out;

public interface DeleteMemberCouponPort {

    void deleteAllByCouponId(Long couponId);
}
