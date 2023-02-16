package com.hexagonal.coupon.adapter.in.web;

import com.hexagonal.coupon.application.port.in.CreateMemberCommand;
import com.hexagonal.coupon.application.port.in.CreateMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class MemberApiController {

    private final CreateMemberUseCase createMemberUseCase;

    @PostMapping("/members")
    void createMember(@RequestParam("name") String name,
                             @RequestParam("phone") String phone,
                             @RequestParam("email") String email) {
        createMemberUseCase.createMember(new CreateMemberCommand(name, phone, email));
    }
}
