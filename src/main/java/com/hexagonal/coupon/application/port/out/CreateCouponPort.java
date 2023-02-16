package com.hexagonal.coupon.application.port.out;

import com.hexagonal.coupon.domain.Coupon;

public interface CreateCouponPort {

    void createCoupon(Coupon coupon);
}
