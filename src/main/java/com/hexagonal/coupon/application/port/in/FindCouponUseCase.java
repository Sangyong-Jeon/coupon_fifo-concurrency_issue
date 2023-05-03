package com.hexagonal.coupon.application.port.in;

public interface FindCouponUseCase {

    FindCouponResponse findCouponByName(String name);
}
