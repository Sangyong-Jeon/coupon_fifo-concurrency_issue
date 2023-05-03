package com.hexagonal.coupon.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

interface CouponRepository extends JpaRepository<CouponJpaEntity, Long>, CouponRepositoryCustom {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<CouponJpaEntity> findLockById(Long id);

    Optional<CouponJpaEntity> findByName(String name);
}
