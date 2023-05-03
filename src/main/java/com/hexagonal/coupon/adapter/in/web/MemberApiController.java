package com.hexagonal.coupon.adapter.in.web;

import com.hexagonal.coupon.application.port.in.CreateMemberCommand;
import com.hexagonal.coupon.application.port.in.CreateMemberUseCase;
import com.hexagonal.coupon.application.port.in.FindMemberResponse;
import com.hexagonal.coupon.application.port.in.FindMemberUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
class MemberApiController {

    private final CreateMemberUseCase createMemberUseCase;
    private final FindMemberUseCase findMemberUseCase;

    @ApiOperation("회원 생성")
    @PostMapping
    void createMember(@Parameter(description = "회원명") @RequestParam("name") String name,
                      @Parameter(description = "휴대전화") @RequestParam("phone") String phone,
                      @Parameter(description = "이메일") @RequestParam("email") String email) {
        createMemberUseCase.createMember(new CreateMemberCommand(name, phone, email));
    }

    @ApiOperation("회원이름으로 회원조회")
    @GetMapping
    FindMemberResponse findMemberById(@Parameter(description = "회원명") @RequestParam String name) {
        return findMemberUseCase.findMemberByName(name);
    }
}
