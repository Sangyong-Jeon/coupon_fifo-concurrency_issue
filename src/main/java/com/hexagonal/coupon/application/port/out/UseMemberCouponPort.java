package com.hexagonal.coupon.application.port.out;

import com.hexagonal.coupon.domain.MemberCoupon;

public interface UseMemberCouponPort {

    void useMemberCoupon(MemberCoupon memberCoupon);
}
