package com.hexagonal.coupon.application.port.in;

import com.hexagonal.coupon.common.SelfValidating;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class CreateMemberCouponResponse extends SelfValidating<CreateMemberCouponResponse> {

    @NotNull
    private final Long memberCouponId;
    @NotNull
    private final Long couponId;
    @NotNull
    private final LocalDateTime createDateTime;

    public CreateMemberCouponResponse(Long memberCouponId, Long couponId, LocalDateTime createDateTime) {
        this.memberCouponId = memberCouponId;
        this.couponId = couponId;
        this.createDateTime = createDateTime;
        this.validateSelf();
    }
}
