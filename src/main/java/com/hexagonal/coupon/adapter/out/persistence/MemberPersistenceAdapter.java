package com.hexagonal.coupon.adapter.out.persistence;

import com.hexagonal.coupon.application.port.out.CreateMemberPort;
import com.hexagonal.coupon.application.port.out.FindMemberPort;
import com.hexagonal.coupon.common.exception.MemberNotExistException;
import com.hexagonal.coupon.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class MemberPersistenceAdapter implements CreateMemberPort, FindMemberPort {

    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;

    @Override
    public void createMember(Member member) {
        MemberJpaEntity memberJpaEntity = memberMapper.mapToJpaEntity(member);
        memberRepository.save(memberJpaEntity);
    }

    @Override
    public Member findMemberByName(String name) {
        MemberJpaEntity memberJpaEntity = memberRepository.findByName(name).orElseThrow(MemberNotExistException::new);
        return memberMapper.mapToDomainEntity(memberJpaEntity);
    }
}
