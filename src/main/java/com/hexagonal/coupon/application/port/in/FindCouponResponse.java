package com.hexagonal.coupon.application.port.in;

import com.hexagonal.coupon.common.SelfValidating;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class FindCouponResponse extends SelfValidating<FindCouponResponse> {

    @NotNull
    private final Long id;

    @NotNull
    private final String name;

    @NotNull
    private final int price;

    @NotNull
    private final int totalQuantity;

    @NotNull
    private final int remainQuantity;
}
