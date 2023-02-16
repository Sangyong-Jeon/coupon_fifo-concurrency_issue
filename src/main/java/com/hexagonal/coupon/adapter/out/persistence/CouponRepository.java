package com.hexagonal.coupon.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface CouponRepository extends JpaRepository<CouponJpaEntity, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update CouponJpaEntity c set c.remainQuantity = c.remainQuantity - 1 where c.id = :id")
    int decreaseRemainQuantityById(@Param("id") Long couponId);
}
