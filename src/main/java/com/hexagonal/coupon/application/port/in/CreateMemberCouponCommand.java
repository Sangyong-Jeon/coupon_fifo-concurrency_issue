package com.hexagonal.coupon.application.port.in;

import com.hexagonal.coupon.common.SelfValidating;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CreateMemberCouponCommand extends SelfValidating<CreateMemberCouponCommand> {

    @NotNull
    private final Long memberId;
    @NotNull
    private final Long couponId;

    public CreateMemberCouponCommand(Long memberId, Long couponId) {
        this.memberId = memberId;
        this.couponId = couponId;
        this.validateSelf();
    }
}
