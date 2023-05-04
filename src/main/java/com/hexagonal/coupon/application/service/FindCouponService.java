package com.hexagonal.coupon.application.service;

import com.hexagonal.coupon.application.port.in.FindCouponResponse;
import com.hexagonal.coupon.application.port.in.FindCouponUseCase;
import com.hexagonal.coupon.application.port.out.FindCouponPort;
import com.hexagonal.coupon.domain.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<FindCouponResponse> findAllCoupons() {
        return findCouponPort.findAllCoupons()
                .stream()
                .map(coupon -> new FindCouponResponse(coupon.getId(), coupon.getName(), coupon.getPrice(), coupon.getTotalQuantity(), coupon.getRemainQuantity()))
                .collect(Collectors.toList());
    }
}
