package com.hexagonal.coupon.application.port.out;

import com.hexagonal.coupon.domain.MemberCoupon;

public interface CreateMemberCouponPort {

    MemberCoupon createMemberCoupon(Long memberId, Long couponId);
}
