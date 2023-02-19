package com.hexagonal.coupon.application.service;

import com.hexagonal.coupon.application.port.in.CreateMemberCouponCommand;
import com.hexagonal.coupon.application.port.in.CreateMemberCouponResponse;
import com.hexagonal.coupon.application.port.in.CreateMemberCouponUseCase;
import com.hexagonal.coupon.application.port.out.FindCouponOfMemberPort;
import com.hexagonal.coupon.application.port.out.LoadCouponPort;
import com.hexagonal.coupon.application.port.out.CreateMemberCouponPort;
import com.hexagonal.coupon.application.port.out.UpdateCouponStatePort;
import com.hexagonal.coupon.common.exception.CouponNotRemainException;
import com.hexagonal.coupon.common.exception.DuplicateCouponException;
import com.hexagonal.coupon.domain.MemberCoupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class CreateMemberCouponService implements CreateMemberCouponUseCase {

    private final LoadCouponPort loadCouponPort;
    private final CreateMemberCouponPort createMemberCouponPort;
    private final UpdateCouponStatePort updateCouponStatePort;
    private final FindCouponOfMemberPort findCouponOfMemberPort;

    @Override
    public CreateMemberCouponResponse createMemberCoupon(CreateMemberCouponCommand command) {
        if (isNotStock(command.getCouponId())) {
            throw new CouponNotRemainException();
        }

        if (isDuplicateCoupon(command)) {
            throw new DuplicateCouponException();
        }

        updateCouponStatePort.decreaseRemainQuantity(command.getCouponId());
        MemberCoupon memberCoupon = createMemberCouponPort.createMemberCoupon(command.getMemberId(), command.getCouponId());
        return new CreateMemberCouponResponse(memberCoupon.getId(), command.getCouponId(), memberCoupon.getCreateDateTime());
    }

    private boolean isNotStock(Long couponId) {
        return loadCouponPort.loadCoupon(couponId).isNotStock();
    }

    private boolean isDuplicateCoupon(CreateMemberCouponCommand command) {
        return findCouponOfMemberPort.findCouponOfMember(command.getMemberId(), command.getCouponId()).isPresent();
    }
}