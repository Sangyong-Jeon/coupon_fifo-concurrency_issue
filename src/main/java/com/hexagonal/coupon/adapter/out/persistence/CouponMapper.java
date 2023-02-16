package com.hexagonal.coupon.adapter.out.persistence;

import com.hexagonal.coupon.domain.Coupon;
import org.springframework.stereotype.Component;

@Component
class CouponMapper {

    Coupon mapToDomainEntity(CouponJpaEntity coupon) {
        return new Coupon(
                coupon.getId(),
                coupon.getName(),
                coupon.getPrice(),
                coupon.getTotalQuantity(),
                coupon.getRemainQuantity()
        );
    }

    CouponJpaEntity mapToJpaEntity(Coupon coupon) {
        return new CouponJpaEntity(
                coupon.getId(),
                coupon.getName(),
                coupon.getPrice(),
                coupon.getTotalQuantity(),
                coupon.getRemainQuantity()
        );
    }
}
