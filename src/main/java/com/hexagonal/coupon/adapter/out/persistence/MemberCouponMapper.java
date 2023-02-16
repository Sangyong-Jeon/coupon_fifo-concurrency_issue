package com.hexagonal.coupon.adapter.out.persistence;

import com.hexagonal.coupon.domain.Coupon;
import com.hexagonal.coupon.domain.MemberCoupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class MemberCouponMapper {

    private final CouponMapper couponMapper;
    private final MemberMapper memberMapper;

    MemberCoupon mapToDomainEntity(MemberCouponJpaEntity memberCoupon) {
        return new MemberCoupon(
                memberCoupon.getId(),
                memberMapper.mapToDomainEntity(memberCoupon.getMember()),
                couponMapper.mapToDomainEntity(memberCoupon.getCoupon()),
                memberCoupon.getUseType(),
                memberCoupon.getCreateDateTime(),
                memberCoupon.getUseDateTime()
        );
    }

    MemberCouponJpaEntity mapToJpaEntity(MemberCoupon memberCoupon) {
        return new MemberCouponJpaEntity(
                memberCoupon.getId(),
                memberMapper.mapToJpaEntity(memberCoupon.getMember()),
                couponMapper.mapToJpaEntity(memberCoupon.getCoupon()),
                memberCoupon.getUseType(),
                memberCoupon.getUseDateTime()
        );
    }
}
