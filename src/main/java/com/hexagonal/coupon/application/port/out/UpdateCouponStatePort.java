package com.hexagonal.coupon.application.port.out;

public interface UpdateCouponStatePort {

    void decreaseRemainQuantity(Long couponId);
}
