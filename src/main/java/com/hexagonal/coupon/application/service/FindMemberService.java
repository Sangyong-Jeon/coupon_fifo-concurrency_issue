package com.hexagonal.coupon.application.service;

import com.hexagonal.coupon.application.port.in.FindMemberResponse;
import com.hexagonal.coupon.application.port.in.FindMemberUseCase;
import com.hexagonal.coupon.application.port.out.FindMemberPort;
import com.hexagonal.coupon.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindMemberService implements FindMemberUseCase {

    private final FindMemberPort findMemberPort;

    @Override
    public FindMemberResponse findMemberByName(String name) {
        Member member = findMemberPort.findMemberByName(name);
        return new FindMemberResponse(member.getId(), member.getName(), member.getPhone(), member.getEmail());
    }
}
