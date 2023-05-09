package com.hexagonal.coupon.adapter.out.persistence;

import com.hexagonal.coupon.application.port.out.CreateCouponPort;
import com.hexagonal.coupon.application.port.out.DeleteCouponPort;
import com.hexagonal.coupon.application.port.out.FindCouponPort;
import com.hexagonal.coupon.application.port.out.LoadCouponPort;
import com.hexagonal.coupon.application.port.out.UpdateCouponStatePort;
import com.hexagonal.coupon.common.exception.CouponNotExistException;
import com.hexagonal.coupon.domain.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class CouponPersistenceAdapter implements LoadCouponPort, CreateCouponPort, UpdateCouponStatePort, DeleteCouponPort, FindCouponPort {

    private final CouponMapper couponMapper;
    private final CouponRepository couponRepository;

    @Override
    public Coupon loadCoupon(Long couponId) {
        CouponJpaEntity couponJpaEntity = couponRepository.findById(couponId)
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

    @Override
    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }

    @Override
    public Coupon findCouponByName(String name) {
        CouponJpaEntity couponJpaEntity = couponRepository.findByName(name).orElseThrow(CouponNotExistException::new);
        return couponMapper.mapToDomainEntity(couponJpaEntity);
    }

    @Override
    public List<Coupon> findAllCoupons() {
        return couponRepository.findAll()
                .stream()
                .map(couponMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }
}
