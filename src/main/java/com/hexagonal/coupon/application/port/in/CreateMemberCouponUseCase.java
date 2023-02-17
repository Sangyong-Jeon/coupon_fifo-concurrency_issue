package com.hexagonal.coupon.application.port.in;

public interface CreateMemberCouponUseCase {

    CreateMemberCouponResponse createMemberCoupon(CreateMemberCouponCommand command);
}
