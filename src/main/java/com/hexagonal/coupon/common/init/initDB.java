package com.hexagonal.coupon.common.init;

import com.hexagonal.coupon.application.port.out.CreateCouponPort;
import com.hexagonal.coupon.application.port.out.CreateMemberPort;
import com.hexagonal.coupon.domain.Coupon;
import com.hexagonal.coupon.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class initDB {

    private final CreateMemberPort createMemberPort;
    private final CreateCouponPort createCouponPort;

    @PostConstruct
    public void init() {
        for (int i = 1; i <= 10000; i++) {
            createMemberPort.createMember(new Member((long) i, "이름" + i, "010-1111-1111", "test@naver.com"));
        }

        for (int i = 1; i <= 10; i++) {
            createCouponPort.createCoupon(new Coupon((long) i, "쿠폰" + i, 1000, 100, 100));
        }

        createCouponPort.createCoupon(new Coupon(11L, "쿠폰11", 10000, 1000, 1000));
    }
}
