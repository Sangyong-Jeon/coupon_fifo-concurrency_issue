package com.hexagonal.coupon.application.port.in;

import com.hexagonal.coupon.common.SelfValidating;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CreateMemberCommand extends SelfValidating<CreateMemberCommand> {

    @NotNull
    private final String name;
    @NotNull
    private final String phone;
    @NotNull
    private final String email;

    public CreateMemberCommand(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.validateSelf();
    }
}
