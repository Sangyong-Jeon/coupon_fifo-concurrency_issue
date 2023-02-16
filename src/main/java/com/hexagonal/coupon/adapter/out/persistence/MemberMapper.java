package com.hexagonal.coupon.adapter.out.persistence;

import com.hexagonal.coupon.domain.Member;
import org.springframework.stereotype.Component;

@Component
class MemberMapper {

    Member mapToDomainEntity(MemberJpaEntity member) {
        return new Member(
                member.getId(),
                member.getName(),
                member.getPhone(),
                member.getEmail()
        );
    }

    MemberJpaEntity mapToJpaEntity(Member member) {
        return new MemberJpaEntity(
                member.getId(),
                member.getName(),
                member.getPhone(),
                member.getEmail()
        );
    }
}
