package com.hexagonal.coupon.adapter.out.persistence;

import com.hexagonal.coupon.application.port.out.LoadCouponPort;
import com.hexagonal.coupon.domain.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponPersistenceAdapter implements LoadCouponPort {

    private final CouponMapper couponMapper;
    private final CouponRepository couponRepository;

    @Override
    public Coupon loadCoupon(Long couponId) {
        CouponJpaEntity couponJpaEntity = couponRepository.findById(couponId)
                .orElseThrow(IllegalArgumentException::new);
        return couponMapper.mapToDomainEntity(couponJpaEntity);
    }
}
