package com.hexagonal.coupon.application.port.out;

import com.hexagonal.coupon.domain.Coupon;

import java.util.List;

public interface FindCouponPort {

    Coupon findCouponByName(String name);

    List<Coupon> findAllCoupons();
}
