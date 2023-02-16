package com.hexagonal.coupon.application.port.in;

import com.hexagonal.coupon.common.SelfValidating;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CreateCouponCommand extends SelfValidating<CreateCouponCommand> {

    @NotNull
    private final String name;
    @NotNull
    private final int price;
    @NotNull
    private final int totalQuantity;
    @NotNull
    private final int remainQuantity;

    public CreateCouponCommand(String name, int price, int totalQuantity, int remainQuantity) {
        this.name = name;
        this.price = price;
        this.totalQuantity = totalQuantity;
        this.remainQuantity = remainQuantity;
        this.validateSelf();
    }
}
