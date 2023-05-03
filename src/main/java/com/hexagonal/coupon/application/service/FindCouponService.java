package com.hexagonal.coupon.application.service;

import com.hexagonal.coupon.application.port.in.FindCouponResponse;
import com.hexagonal.coupon.application.port.in.FindCouponUseCase;
import com.hexagonal.coupon.application.port.out.FindCouponPort;
import com.hexagonal.coupon.domain.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindCouponService implements FindCouponUseCase {

    private final FindCouponPort findCouponPort;

    @Override
    public FindCouponResponse findCouponByName(String name) {
        Coupon coupon = findCouponPort.findCouponByName(name);
        return new FindCouponResponse(coupon.getId(), coupon.getName(), coupon.getPrice(), coupon.getTotalQuantity(), coupon.getRemainQuantity());
    }
}
