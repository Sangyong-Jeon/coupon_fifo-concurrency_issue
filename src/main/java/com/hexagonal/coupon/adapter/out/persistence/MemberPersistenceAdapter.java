package com.hexagonal.coupon.adapter.out.persistence;

import com.hexagonal.coupon.application.port.out.CreateMemberPort;
import com.hexagonal.coupon.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class MemberPersistenceAdapter implements CreateMemberPort {

    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;

    @Override
    public void createMember(Member member) {
        MemberJpaEntity memberJpaEntity = memberMapper.mapToJpaEntity(member);
        memberRepository.save(memberJpaEntity);
    }
}
