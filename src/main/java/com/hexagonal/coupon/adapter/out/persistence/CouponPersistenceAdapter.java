package com.hexagonal.coupon.adapter.out.persistence;

import com.hexagonal.coupon.application.port.out.CreateCouponPort;
import com.hexagonal.coupon.application.port.out.LoadCouponPort;
import com.hexagonal.coupon.domain.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CouponPersistenceAdapter implements LoadCouponPort, CreateCouponPort {

    private final CouponMapper couponMapper;
    private final CouponRepository couponRepository;

    @Override
    public Coupon loadCoupon(Long couponId) {
        CouponJpaEntity couponJpaEntity = couponRepository.findById(couponId)
                .orElseThrow(IllegalArgumentException::new);
        return couponMapper.mapToDomainEntity(couponJpaEntity);
    }

    @Override
    public void createCoupon(Coupon coupon) {
        couponRepository.save(couponMapper.mapToJpaEntity(coupon));
    }
}
