package com.hexagonal.coupon.adapter.out.persistence;

interface CouponRepositoryCustom {

    long decreaseRemainQuantityById(Long couponId);
}
