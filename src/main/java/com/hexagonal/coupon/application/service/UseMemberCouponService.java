package com.hexagonal.coupon.application.service;

import com.hexagonal.coupon.application.port.in.UseMemberCouponCommand;
import com.hexagonal.coupon.application.port.in.UseMemberCouponResponse;
import com.hexagonal.coupon.application.port.in.UseMemberCouponUseCase;
import com.hexagonal.coupon.application.port.out.FindCouponOfMemberPort;
import com.hexagonal.coupon.application.port.out.UseMemberCouponPort;
import com.hexagonal.coupon.domain.MemberCoupon;
import com.hexagonal.coupon.domain.UseType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class UseMemberCouponService implements UseMemberCouponUseCase {

    private final UseMemberCouponPort useMemberCouponPort;
    private final FindCouponOfMemberPort findCouponOfMemberPort;

    @Override
    public UseMemberCouponResponse useMemberCoupon(UseMemberCouponCommand command) {
        MemberCoupon memberCoupon = findCouponOfMemberPort.findCouponOfMember(command.getMemberId(), command.getCouponId())
                .orElseThrow(IllegalArgumentException::new);
        if (UseType.USE.equals(memberCoupon.getUseType())) {
            throw new IllegalStateException();
        }
        MemberCoupon useMemberCoupon = MemberCoupon.use(memberCoupon);
        useMemberCouponPort.useMemberCoupon(useMemberCoupon);
        return new UseMemberCouponResponse(useMemberCoupon.getId(), command.getCouponId(), useMemberCoupon.getUseDateTime());
    }
}
