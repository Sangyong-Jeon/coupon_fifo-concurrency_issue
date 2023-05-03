package com.hexagonal.coupon.application.port.out;

import com.hexagonal.coupon.domain.Coupon;

public interface FindCouponPort {

    Coupon findCouponByName(String name);
}
