package com.hexagonal.coupon.adapter.out.persistence;

import org.springframework.data.repository.query.Param;

interface CouponRepositoryCustom {

    long decreaseRemainQuantityById(@Param("id") Long couponId);
}
