package com.hexagonal.coupon.application.port.out;

import com.hexagonal.coupon.domain.MemberCoupon;

import java.util.List;

public interface LoadAllCouponsOfMemberPort {

    List<MemberCoupon> loadAllCouponsOfMember(Long memberId);
}
