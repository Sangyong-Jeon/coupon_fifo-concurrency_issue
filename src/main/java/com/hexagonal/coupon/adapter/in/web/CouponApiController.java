package com.hexagonal.coupon.adapter.in.web;

import com.hexagonal.coupon.application.port.in.CreateCouponCommand;
import com.hexagonal.coupon.application.port.in.CreateCouponUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class CouponApiController {

    private final CreateCouponUseCase createCouponUseCase;

    @ApiOperation("쿠폰 생성")
    @PostMapping("/coupons")
    void createCoupon(@Parameter(description = "쿠폰명") @RequestParam String name,
                      @Parameter(description = "쿠폰가격") @RequestParam int price,
                      @Parameter(description = "쿠폰전체개수") @RequestParam int totalQuantity,
                      @Parameter(description = "쿠폰남은개수") @RequestParam int remainQuantity) {
        createCouponUseCase.createCoupon(new CreateCouponCommand(name, price, totalQuantity, remainQuantity));
    }
}
