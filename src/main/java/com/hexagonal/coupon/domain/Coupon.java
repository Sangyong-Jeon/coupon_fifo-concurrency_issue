package com.hexagonal.coupon.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Coupon {

    private final Long id;
    private final String name;
    private final int price;
    private final int totalQuantity;
    private final int remainQuantity;

    public boolean isStock() {
        return totalQuantity > 0 && remainQuantity > 0;
    }

    public boolean isNotStock() {
        return !isStock();
    }
}
