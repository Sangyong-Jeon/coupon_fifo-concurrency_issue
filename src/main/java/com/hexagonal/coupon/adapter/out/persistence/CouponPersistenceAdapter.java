package com.hexagonal.coupon.adapter.out.persistence;

import com.hexagonal.coupon.application.port.out.CreateCouponPort;
import com.hexagonal.coupon.application.port.out.LoadCouponPort;
import com.hexagonal.coupon.application.port.out.UpdateCouponStatePort;
import com.hexagonal.coupon.common.exception.CouponNotExistException;
import com.hexagonal.coupon.domain.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CouponPersistenceAdapter implements LoadCouponPort, CreateCouponPort, UpdateCouponStatePort {

    private final CouponMapper couponMapper;
    private final CouponRepository couponRepository;

    @Override
    public Coupon loadCoupon(Long couponId) {
        CouponJpaEntity couponJpaEntity = couponRepository.findLockById(couponId)
                .orElseThrow(CouponNotExistException::new);
        return couponMapper.mapToDomainEntity(couponJpaEntity);
    }

    @Override
    public void createCoupon(Coupon coupon) {
        couponRepository.save(couponMapper.mapToJpaEntity(coupon));
    }

    @Override
    public void decreaseRemainQuantity(Long couponId) {
        couponRepository.decreaseRemainQuantityById(couponId);
    }
}
