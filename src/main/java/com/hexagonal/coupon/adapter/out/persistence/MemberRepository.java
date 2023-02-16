package com.hexagonal.coupon.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface MemberRepository extends JpaRepository<MemberJpaEntity, Long> {
}
