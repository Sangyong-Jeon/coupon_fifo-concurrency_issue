package com.hexagonal.coupon.application.port.in;

import com.hexagonal.coupon.common.SelfValidating;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UseMemberCouponCommand extends SelfValidating<UseMemberCouponCommand> {

    @NotNull
    private final Long memberId;
    @NotNull
    private final Long couponId;

    public UseMemberCouponCommand(Long memberId, Long couponId) {
        this.memberId = memberId;
        this.couponId = couponId;
        this.validateSelf();
    }
}
