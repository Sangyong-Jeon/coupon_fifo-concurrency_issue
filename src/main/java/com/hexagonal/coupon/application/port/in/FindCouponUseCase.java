package com.hexagonal.coupon.application.port.in;

import java.util.List;

public interface FindCouponUseCase {

    FindCouponResponse findCouponByName(String name);

    List<FindCouponResponse> findAllCoupons();
}
