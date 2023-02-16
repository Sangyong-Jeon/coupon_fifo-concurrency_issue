package com.hexagonal.coupon.adapter.in.web;

import com.hexagonal.coupon.application.port.in.CreateMemberCouponCommand;
import com.hexagonal.coupon.application.port.in.CreateMemberCouponUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class MemberCouponApiController {

    private final CreateMemberCouponUseCase createMemberCouponUseCase;

    @PostMapping("/members/{memberId}/coupons/{couponId}")
    boolean createMemberCoupon(@PathVariable Long memberId,
                               @PathVariable Long couponId) {
        return createMemberCouponUseCase.createMemberCoupon(new CreateMemberCouponCommand(memberId, couponId));
    }
}
