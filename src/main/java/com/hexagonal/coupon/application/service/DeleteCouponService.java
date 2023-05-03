package com.hexagonal.coupon.application.service;

import com.hexagonal.coupon.application.port.in.DeleteCouponUseCase;
import com.hexagonal.coupon.application.port.out.DeleteCouponPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteCouponService implements DeleteCouponUseCase {

    private final DeleteCouponPort deleteCouponPort;

    @Override
    public void deleteCoupon(Long id) {
        deleteCouponPort.deleteCoupon(id);
    }
}
