package com.hexagonal.coupon.adapter.in.web;

import com.hexagonal.coupon.application.port.in.CreateCouponCommand;
import com.hexagonal.coupon.application.port.in.CreateCouponUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class CouponApiController {

    private final CreateCouponUseCase createCouponUseCase;

    @PostMapping("/coupons")
    void createCoupon(@RequestParam String name,
                      @RequestParam int price,
                      @RequestParam int totalQuantity,
                      @RequestParam int remainQuantity) {
        createCouponUseCase.createCoupon(new CreateCouponCommand(name, price, totalQuantity, remainQuantity));
    }
}
