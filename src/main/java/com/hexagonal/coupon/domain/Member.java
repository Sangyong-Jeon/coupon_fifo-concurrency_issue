package com.hexagonal.coupon.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Member {

    private final Long id;
    private final String name;
    private final String phone;
    private final String email;
}
