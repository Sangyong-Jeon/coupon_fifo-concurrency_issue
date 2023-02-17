package com.hexagonal.coupon.application.port.in;

import com.hexagonal.coupon.common.SelfValidating;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class UseMemberCouponResponse extends SelfValidating<UseMemberCouponResponse> {

    @NotNull
    private final Long memberCouponId;
    @NotNull
    private final Long couponId;
    @NotNull
    private final LocalDateTime useDateTime;

    public UseMemberCouponResponse(Long memberCouponId, Long couponId, LocalDateTime useDateTime) {
        this.memberCouponId = memberCouponId;
        this.couponId = couponId;
        this.useDateTime = useDateTime;
        this.validateSelf();
    }
}
