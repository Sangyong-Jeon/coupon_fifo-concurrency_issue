package com.hexagonal.coupon.application.port.out;

import com.hexagonal.coupon.domain.MemberCoupon;

public interface FindCouponOfMemberPort {

    MemberCoupon findCouponOfMember(Long memberId, Long couponId);
}
