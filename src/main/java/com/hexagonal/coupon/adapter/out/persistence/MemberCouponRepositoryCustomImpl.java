package com.hexagonal.coupon.adapter.out.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.hexagonal.coupon.adapter.out.persistence.QCouponJpaEntity.*;
import static com.hexagonal.coupon.adapter.out.persistence.QMemberCouponJpaEntity.*;
import static com.hexagonal.coupon.adapter.out.persistence.QMemberJpaEntity.*;

@RequiredArgsConstructor
class MemberCouponRepositoryCustomImpl implements MemberCouponRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemberCouponJpaEntity> findAllByMemberId(Long memberId) {
        return queryFactory
                .selectFrom(memberCouponJpaEntity)
                .join(memberCouponJpaEntity.member, memberJpaEntity).fetchJoin()
                .join(memberCouponJpaEntity.coupon, couponJpaEntity).fetchJoin()
                .where(memberIdEq(memberId))
                .fetch();
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return (memberId != null) ? memberCouponJpaEntity.member.id.eq(memberId) : null;
    }

    @Override
    public Optional<MemberCouponJpaEntity> findByMemberIdAndCouponId(Long memberId, Long couponId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(memberCouponJpaEntity)
                .join(memberCouponJpaEntity.member, memberJpaEntity).fetchJoin()
                .join(memberCouponJpaEntity.coupon, couponJpaEntity).fetchJoin()
                .where(memberIdEq(memberId), couponIdEq(couponId))
                .fetchOne());
    }

    private BooleanExpression couponIdEq(Long couponId) {
        return (couponId != null) ? memberCouponJpaEntity.coupon.id.eq(couponId) : null;
    }
}
