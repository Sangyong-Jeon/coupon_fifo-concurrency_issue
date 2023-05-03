package com.hexagonal.coupon.application.port.in;

import com.hexagonal.coupon.common.SelfValidating;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class FindMemberResponse extends SelfValidating<FindMemberResponse> {

    @NotNull
    private final Long id;

    @NotNull
    private final String name;

    @NotNull
    private final String phone;

    @NotNull
    private final String email;
}
