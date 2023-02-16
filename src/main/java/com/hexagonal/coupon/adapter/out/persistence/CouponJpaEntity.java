package com.hexagonal.coupon.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COUPON")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
class CouponJpaEntity extends BaseTimeJpaEntity {

    @Id
    @Column(name = "COU_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "COU_NAME")
    private String name;

    @Column(name = "COU_PRICE")
    private int price;

    @Column(name = "COU_TOTAL_QUANTITY")
    private int totalQuantity;

    @Column(name = "COU_REMAIN_QUANTITY")
    private int remainQuantity;

    static CouponJpaEntity withId(Long couponId) {
        return new CouponJpaEntity(couponId, null, 0, 0, 0);
    }
}
