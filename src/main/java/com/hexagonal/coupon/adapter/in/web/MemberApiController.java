package com.hexagonal.coupon.adapter.in.web;

import com.hexagonal.coupon.application.port.in.CreateMemberCommand;
import com.hexagonal.coupon.application.port.in.CreateMemberUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class MemberApiController {

    private final CreateMemberUseCase createMemberUseCase;

    @ApiOperation("회원 생성")
    @PostMapping("/members")
    void createMember(@Parameter(description = "회원명") @RequestParam("name") String name,
                      @Parameter(description = "휴대전화") @RequestParam("phone") String phone,
                      @Parameter(description = "이메일") @RequestParam("email") String email) {
        createMemberUseCase.createMember(new CreateMemberCommand(name, phone, email));
    }
}
