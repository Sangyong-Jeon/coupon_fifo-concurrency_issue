package com.hexagonal.coupon.adapter.in.web;

import com.hexagonal.coupon.application.port.in.CreateMemberCouponCommand;
import com.hexagonal.coupon.application.port.in.CreateMemberCouponUseCase;
import com.hexagonal.coupon.application.port.in.UseMemberCouponCommand;
import com.hexagonal.coupon.application.port.in.UseMemberCouponResponse;
import com.hexagonal.coupon.application.port.in.UseMemberCouponUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class MemberCouponApiController {

    private final CreateMemberCouponUseCase createMemberCouponUseCase;
    private final UseMemberCouponUseCase useMemberCouponUseCase;

    @PostMapping("/members/{memberId}/coupons/{couponId}")
    boolean createMemberCoupon(@PathVariable Long memberId,
                               @PathVariable Long couponId) {
        return createMemberCouponUseCase.createMemberCoupon(new CreateMemberCouponCommand(memberId, couponId));
    }

    @PatchMapping("/members/{memberId}/coupons/{couponId}")
    UseMemberCouponResponse useMemberCoupon(@PathVariable Long memberId,
                                            @PathVariable Long couponId) {
        return useMemberCouponUseCase.useMemberCoupon(new UseMemberCouponCommand(memberId, couponId));
    }
}
