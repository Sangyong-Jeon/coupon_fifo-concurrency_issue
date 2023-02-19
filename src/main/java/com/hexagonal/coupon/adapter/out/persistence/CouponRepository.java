package com.hexagonal.coupon.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface CouponRepository extends JpaRepository<CouponJpaEntity, Long>, CouponRepositoryCustom {
}
