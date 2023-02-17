package com.hexagonal.coupon.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberCoupon {
    private final Long id;
    private final Member member;
    private final Coupon coupon;
    private final UseType useType;
    private final LocalDateTime createDateTime;
    private final LocalDateTime useDateTime;

    public static MemberCoupon use(MemberCoupon memberCoupon) {
        return new MemberCoupon(
                memberCoupon.getId(),
                memberCoupon.getMember(),
                memberCoupon.getCoupon(),
                UseType.USE,
                memberCoupon.createDateTime,
                LocalDateTime.now()
        );
    }
}
