package com.hexagonal.coupon.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;


interface MemberCouponRepository extends JpaRepository<MemberCouponJpaEntity, Long>, MemberCouponRepositoryCustom {
}
