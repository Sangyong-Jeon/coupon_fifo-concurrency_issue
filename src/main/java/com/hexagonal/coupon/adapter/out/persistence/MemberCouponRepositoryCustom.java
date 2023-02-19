package com.hexagonal.coupon.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

interface MemberCouponRepositoryCustom {

    List<MemberCouponJpaEntity> findAllByMemberId(Long memberId);

    Optional<MemberCouponJpaEntity> findByMemberIdAndCouponId(Long memberId, Long couponId);
}
