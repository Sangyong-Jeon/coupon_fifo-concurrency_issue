package com.hexagonal.coupon.adapter.out.persistence;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface MemberCouponRepository extends JpaRepository<MemberCouponJpaEntity, Long> {

    @Query("select mc from MemberCouponJpaEntity mc " +
            "join fetch mc.member " +
            "join fetch mc.coupon " +
            "where mc.member.id = :memberId")
    List<MemberCouponJpaEntity> findAllByMemberId(@Param("memberId") Long memberId);

    @EntityGraph(attributePaths = {"member", "coupon"})
    Optional<MemberCouponJpaEntity> findByMemberIdAndCouponId(Long memberId, Long couponId);
}
