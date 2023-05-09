package com.hexagonal.coupon.application.service;

import com.hexagonal.coupon.application.port.in.DeleteCouponUseCase;
import com.hexagonal.coupon.application.port.out.DeleteCouponPort;
import com.hexagonal.coupon.application.port.out.DeleteMemberCouponPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteCouponService implements DeleteCouponUseCase {

    private final DeleteCouponPort deleteCouponPort;
    private final DeleteMemberCouponPort deleteMemberCouponPort;

    @Override
    public void deleteCoupon(Long id) {
        deleteMemberCouponPort.deleteAllByCouponId(id);
        deleteCouponPort.deleteCoupon(id);
    }
}
