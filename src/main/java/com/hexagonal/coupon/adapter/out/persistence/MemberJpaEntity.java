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
import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
class MemberJpaEntity extends BaseTimeJpaEntity {

    @Id
    @Column(name = "MEM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "MEM_NAME")
    private String name;

    @Column(name = "MEM_PHONE")
    private String phone;

    @Column(name = "MEM_EMAIL")
    private String email;

    static MemberJpaEntity withId(Long memberId) {
        return new MemberJpaEntity(memberId, null, null, null);
    }
}
