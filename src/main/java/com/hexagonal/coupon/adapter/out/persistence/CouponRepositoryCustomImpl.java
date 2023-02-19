package com.hexagonal.coupon.adapter.out.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.hexagonal.coupon.adapter.out.persistence.QCouponJpaEntity.*;

@RequiredArgsConstructor
class CouponRepositoryCustomImpl implements CouponRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public long decreaseRemainQuantityById(Long couponId) {
        return queryFactory
                .update(couponJpaEntity)
                .set(couponJpaEntity.remainQuantity, couponJpaEntity.remainQuantity.subtract(1))
                .where(couponJpaEntity.id.eq(couponId))
                .execute();
    }
}
