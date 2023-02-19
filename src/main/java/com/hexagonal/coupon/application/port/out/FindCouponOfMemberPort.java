package com.hexagonal.coupon.application.port.out;

import com.hexagonal.coupon.domain.MemberCoupon;

import java.util.Optional;

public interface FindCouponOfMemberPort {

    Optional<MemberCoupon> findCouponOfMember(Long memberId, Long couponId);
}
