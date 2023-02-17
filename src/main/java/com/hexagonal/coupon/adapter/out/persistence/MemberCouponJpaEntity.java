package com.hexagonal.coupon.adapter.out.persistence;

import com.hexagonal.coupon.domain.UseType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER_COUPON")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
class MemberCouponJpaEntity extends BaseTimeJpaEntity {

    @Id
    @Column(name = "MEM_COU_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEM_ID")
    private MemberJpaEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COU_ID")
    private CouponJpaEntity coupon;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEM_COU_USE")
    private UseType useType;

    @Column(name = "MEM_COU_USE_DATETIME")
    private LocalDateTime useDateTime;

    static MemberCouponJpaEntity withMemberIdAndCouponId(Long memberId, Long couponId) {
        return new MemberCouponJpaEntity(null, MemberJpaEntity.withId(memberId), CouponJpaEntity.withId(couponId), UseType.UNUSED, null);
    }
}
