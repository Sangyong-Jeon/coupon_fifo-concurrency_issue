package com.hexagonal.coupon.adapter.in.web;

import com.hexagonal.coupon.application.port.in.CreateCouponCommand;
import com.hexagonal.coupon.application.port.in.CreateCouponUseCase;
import com.hexagonal.coupon.application.port.in.DeleteCouponUseCase;
import com.hexagonal.coupon.application.port.in.FindCouponResponse;
import com.hexagonal.coupon.application.port.in.FindCouponUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupons")
class CouponApiController {

    private final CreateCouponUseCase createCouponUseCase;
    private final FindCouponUseCase findCouponUseCase;
    private final DeleteCouponUseCase deleteCouponUseCase;

    @ApiOperation("쿠폰 생성")
    @PostMapping
    void createCoupon(@Parameter(description = "쿠폰명") @RequestParam String name,
                      @Parameter(description = "쿠폰가격") @RequestParam int price,
                      @Parameter(description = "쿠폰전체개수") @RequestParam int totalQuantity,
                      @Parameter(description = "쿠폰남은개수") @RequestParam int remainQuantity) {
        createCouponUseCase.createCoupon(new CreateCouponCommand(name, price, totalQuantity, remainQuantity));
    }

    @ApiOperation("쿠폰 삭제")
    @DeleteMapping
    void deleteCoupon(@Parameter(description = "쿠폰아이디") @RequestParam Long id) {
        deleteCouponUseCase.deleteCoupon(id);
    }

    @ApiOperation("쿠폰이름으로 쿠폰조회")
    @GetMapping("/{name}")
    FindCouponResponse findCouponByName(@Parameter(description = "쿠폰명") @PathVariable String name) {
        return findCouponUseCase.findCouponByName(name);
    }

    @ApiOperation("쿠폰 전체조회")
    @GetMapping
    List<FindCouponResponse> findAllCoupons() {
        return findCouponUseCase.findAllCoupons();
    }
}
