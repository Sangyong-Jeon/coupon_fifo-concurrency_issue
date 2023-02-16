package com.hexagonal.coupon.application.service;

import com.hexagonal.coupon.application.port.in.CreateMemberCommand;
import com.hexagonal.coupon.application.port.in.CreateMemberUseCase;
import com.hexagonal.coupon.application.port.out.CreateMemberPort;
import com.hexagonal.coupon.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class CreateMemberService implements CreateMemberUseCase {

    private final CreateMemberPort createMemberPort;

    @Override
    public void createMember(CreateMemberCommand command) {
        createMemberPort.createMember(new Member(null, command.getName(), command.getPhone(), command.getEmail()));
    }
}
