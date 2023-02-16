package com.hexagonal.coupon.application.service;

import com.hexagonal.coupon.application.port.in.CreateCouponCommand;
import com.hexagonal.coupon.application.port.in.CreateCouponUseCase;
import com.hexagonal.coupon.application.port.out.CreateCouponPort;
import com.hexagonal.coupon.domain.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class CreateCouponService implements CreateCouponUseCase {

    private final CreateCouponPort createCouponPort;

    @Override
    public void createCoupon(CreateCouponCommand command) {
        createCouponPort.createCoupon(new Coupon(
                null,
                command.getName(),
                command.getPrice(),
                command.getTotalQuantity(),
                command.getRemainQuantity()));
    }
}
